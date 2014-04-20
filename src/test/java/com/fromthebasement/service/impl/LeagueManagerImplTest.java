package com.fromthebasement.service.impl;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.dao.SurveyDao;
import com.fromthebasement.model.Survey;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;

/**
 * Created by jeffginn on 4/19/14.
 */
public class LeagueManagerImplTest extends BaseManagerMockTestCase {
    @InjectMocks
    private LeagueManagerImpl manager;

    @Mock
    private LeagueDao dao;

    @Test
    public void testGetLeagues() {
        log.debug("testing getAll...");
        //given
        final List leagues = new ArrayList();
        given(dao.getAll()).willReturn(leagues);
        //when
        List result = manager.getAll();
        //then
        assertSame(leagues, result);
    }
}
