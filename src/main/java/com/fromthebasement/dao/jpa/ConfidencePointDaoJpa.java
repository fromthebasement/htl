package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.ConfidencePointDao;
import com.fromthebasement.model.ConfidencePoint;
import org.springframework.stereotype.Repository;

/**
 * Created by jeffginn on 8/23/14.
 */
@Repository("confidencePointDao")
public class ConfidencePointDaoJpa extends GenericDaoJpa<ConfidencePoint, Long> implements ConfidencePointDao {

    public ConfidencePointDaoJpa() {
        super(ConfidencePoint.class);
    }
}
