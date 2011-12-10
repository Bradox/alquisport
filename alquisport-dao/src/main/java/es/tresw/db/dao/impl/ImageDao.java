package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_ImageDao;
import es.tresw.db.entities.Image;

@Repository("imageDao")
public class ImageDao extends GenericDao<Image, Long> implements  I_ImageDao {

}
