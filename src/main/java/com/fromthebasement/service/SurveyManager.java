package com.fromthebasement.service;

import com.fromthebasement.model.Question;
import com.fromthebasement.model.Survey;

import javax.ws.rs.Path;

/**
 * Created by jeffginn on 4/14/14.
 */
@Path("/surveys")
public interface SurveyManager extends GenericManager<Survey,Long> {
}
