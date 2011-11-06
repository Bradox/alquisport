package es.tresw.db.beans;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="userId")
public class Client extends User
{

}
