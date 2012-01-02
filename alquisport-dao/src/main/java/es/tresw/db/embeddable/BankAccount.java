package es.tresw.db.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BankAccount 
{
	
	@Column(name="ENTITY_CODE", length=4)
	private Integer entityCode;
	@Column(name="CONTROL_CODE", length=2)
	private Integer controlCode;
	@Column(name="OFFICE_CODE", length=4)
	private Integer officeCode;
	@Column(name="ACCOUNT_NUMBER", length=10)
	private Integer accountNumber;
	
	public BankAccount()
	{
		
	}

	public BankAccount(Integer entityCode, Integer controlCode,Integer officeCode, Integer accountNumber)
	{
		super();
		this.entityCode = entityCode;
		this.controlCode = controlCode;
		this.officeCode = officeCode;
		this.accountNumber = accountNumber;
	}

	public Integer getEntityCode() 
	{
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) 
	{
		this.entityCode = entityCode;
	}

	public Integer getControlCode() 
	{
		return controlCode;
	}

	public void setControlCode(Integer controlCode) 
	{
		this.controlCode = controlCode;
	}

	public Integer getOfficeCode() 
	{
		return officeCode;
	}

	public void setOfficeCode(Integer officeCode) 
	{
		this.officeCode = officeCode;
	}

	public Integer getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) 
	{
		this.accountNumber = accountNumber;
	}
	
}
