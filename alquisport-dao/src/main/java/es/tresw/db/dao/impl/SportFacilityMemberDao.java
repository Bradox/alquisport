package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_SportFacilityMemberDao;
import es.tresw.db.entities.SportFacilityMember;


@Repository("sportFacilityMemberDao")
public class SportFacilityMemberDao extends GenericDao<SportFacilityMember, Long> implements  I_SportFacilityMemberDao {

}
