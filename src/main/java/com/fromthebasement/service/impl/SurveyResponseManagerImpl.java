package com.fromthebasement.service.impl;

import com.fromthebasement.dao.AnswerDao;
import com.fromthebasement.dao.ConfidencePointDao;
import com.fromthebasement.dao.SurveyResponseDao;
import com.fromthebasement.model.*;
import com.fromthebasement.service.SurveyResponseManager;
import com.fromthebasement.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by jeffginn on 7/26/14.
 */
@Service("surveyResponseManager")
public class SurveyResponseManagerImpl extends GenericManagerImpl<SurveyResponse,Long> implements SurveyResponseManager {
    UserManager         userManager;
    SurveyResponseDao   surveyResponseDao;
    AnswerDao           answerDao;
    ConfidencePointDao  confidencePointDao;

    @Autowired
    public SurveyResponseManagerImpl(SurveyResponseDao surveyResponseDao, UserManager userManager, AnswerDao answerDao, ConfidencePointDao confidencePointDao) {
        super(surveyResponseDao);
        this.surveyResponseDao = surveyResponseDao;
        this.userManager = userManager;
        this.answerDao = answerDao;
        this.confidencePointDao = confidencePointDao;
    }

    @Override
    public List<SurveyResponse> getAllActive() {
        return surveyResponseDao.getAllActive(UserManagerImpl.getCurrentUser().getId());
    }

    @Override
    public SurveyResponseForm get(long leaguePlayerId, long surveyId) {
        SurveyResponse surveyResponse =  surveyResponseDao.get(leaguePlayerId,surveyId);

        return getSurveyResponseForm(surveyResponse);
    }

    private SurveyResponseForm getSurveyResponseForm(SurveyResponse surveyResponse) {
        SurveyResponseForm surveyResponseForm = new SurveyResponseForm();
        surveyResponseForm.setSurveyResponse( surveyResponse );

        Map<Long,SurveyResponseFormEntry> entries = new HashMap<>();

        for( Question question : surveyResponse.getSurvey().getQuestions() ) {
            SurveyResponseFormEntry surveyResponseFormEntry = new SurveyResponseFormEntry();
            surveyResponseFormEntry.setQuestion( question );
            entries.put( question.getId(), surveyResponseFormEntry );
        }

        if( surveyResponse.getConfidencePoints() != null ) {
            for (ConfidencePoint confidencePoint : surveyResponse.getConfidencePoints()) {
                Question question = confidencePoint.getQuestion();
                SurveyResponseFormEntry surveyResponseFormEntry = entries.get(question.getId());
                surveyResponseFormEntry.setScore( confidencePoint.getScore() );

                if( confidencePoint.getAnswer() != null ) {
                    surveyResponseFormEntry.setAnswerId(confidencePoint.getAnswer().getId());
                }
            }
        }

        for ( SurveyResponseFormEntry surveyResponseFormEntry : entries.values() )
            surveyResponseForm.addEntry(surveyResponseFormEntry);

        Collections.sort(surveyResponseForm.getEntries(), new SurveyResponseFormEntryComparator());

        return surveyResponseForm;
    }

    @Override
    public SurveyResponseForm save(SurveyResponseForm surveyResponseForm) {
        SurveyResponse _existingResponse = null;

        if( surveyResponseForm.getSurveyResponse().getId() != null )
            _existingResponse = surveyResponseDao.get( surveyResponseForm.getSurveyResponse().getId() );
        else
            _existingResponse = surveyResponseDao.get( surveyResponseForm.getSurveyResponse().getLeaguePlayer().getId(),
                                                       surveyResponseForm.getSurveyResponse().getSurvey().getId() );

        if( _existingResponse == null )
            throw new WebApplicationException( Response.Status.BAD_REQUEST );

        List<ConfidencePoint> confidencePoints = new ArrayList<ConfidencePoint>();
        for( int i = 0; i < surveyResponseForm.getEntries().size(); i++ )
        {
            SurveyResponseFormEntry surveyResponseFormEntry = surveyResponseForm.getEntries().get(i);

            ConfidencePoint confidencePoint = new ConfidencePoint();
            confidencePoint.setSurveyResponse( _existingResponse );

            Question question = surveyResponseFormEntry.getQuestion();
            confidencePoint.setQuestion( question );

            if( surveyResponseFormEntry.getAnswerId() != -1 ) {
                Answer answer = answerDao.get(surveyResponseFormEntry.getAnswerId());

                if (answer == null || answer.getQuestion().getId() != question.getId())
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);

                confidencePoint.setAnswer(answer);
            }

            confidencePoint.setScore( surveyResponseForm.getEntries().size() - i );

            confidencePoints.add(confidencePoint);
        }

        if( _existingResponse.getConfidencePoints() != null ) {
            for( ConfidencePoint confidencePoint : _existingResponse.getConfidencePoints() )
                confidencePointDao.remove(confidencePoint.getId());
        }

        _existingResponse.setConfidencePoints(confidencePoints);

        _existingResponse = surveyResponseDao.save(_existingResponse);

        return getSurveyResponseForm( _existingResponse );
    }
}
