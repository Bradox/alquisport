package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_FeatureDao;
import es.tresw.db.entities.Feature;

@Repository("featureDao")
public class FeatureDao extends GenericDao<Feature, Long> implements  I_FeatureDao {

}
