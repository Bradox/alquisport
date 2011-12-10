package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.dao.I_ProvinceDao;
import es.tresw.db.entities.Province;

@Repository("provinceDao")
public class ProvinceDao extends GenericDao<Province, Long> implements  I_ProvinceDao {

}
