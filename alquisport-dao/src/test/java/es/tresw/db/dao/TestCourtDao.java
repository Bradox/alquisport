package es.tresw.db.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.embeddable.CourtType;
import es.tresw.db.embeddable.ReservationConfig;
import es.tresw.db.entities.Court;
import es.tresw.db.entities.Feature;
import es.tresw.db.entities.Schedule;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestCourtDao{
	

	@Autowired
	private I_FeatureDao featureDao;
	@Autowired
	private I_CourtDao courtDao;
	@Autowired
	private I_ScheduleDao scheduleDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Court court = new Court();
		CourtType courtType = new CourtType();
		courtType.setDescription("To guapo");
		courtType.setQuantity(10);
		court.setCourtType(courtType);
		court.setDescription("asdasdsadas asdas dasd sad");
		Feature feature = new Feature();
		feature.setKey("Sergio");
		feature.setPosition(1);
		feature.setValue("PUTA");
		Set<Feature> features = new HashSet<Feature>();
		features.add(feature);
		featureDao.create(feature);
		court.setFeatures(features);
		ReservationConfig reservationConfig = new ReservationConfig();
		reservationConfig.setDaysClient(15);
		reservationConfig.setDaysMember(2);
		reservationConfig.setHourLightsOn(20);
		reservationConfig.setReservationType(1);
		court.setReservationConfig(reservationConfig);
		courtDao.create(court);
		Schedule schedule = new Schedule();
		schedule.setEndHour(22);
		schedule.setMinEnd(00);
		schedule.setMinStart(00);
		schedule.setPriceCourt(10);
		schedule.setPriceLight(5);
		schedule.setStartHour(15);
		scheduleDao.create(schedule);
	}
	
	@Test
	public void testReadAll()
	{
		assertNotNull(scheduleDao.readAll());
	}
	
	@Test
	public void testReadOne()
	{
		Long id = courtDao.readAll().get(0).getId();
		assertNotNull(courtDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Court courtDelete = courtDao.readAll().get(0);
		Long idDelete = courtDelete.getId();
		courtDao.delete(courtDelete);
		assertNull(courtDao.read(idDelete));
	}

		
	public void setFeatureDao(I_FeatureDao featureDao) 
	{
		this.featureDao = featureDao;
	}

	public void setCourtDao(I_CourtDao courtDao)
	{
		this.courtDao=courtDao;
	}

	public void setScheduleDao(I_ScheduleDao scheduleDao) 
	{
		this.scheduleDao = scheduleDao;
	}
	
}
