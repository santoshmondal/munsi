package com.license.tool;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="license")
@XmlAccessorType(XmlAccessType.FIELD)
public class License implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @XmlElement private String startDate			= "";
    @XmlElement private String endDate				= "";
    @XmlElement private String hostname				= "";	
    @XmlElement private String machineName			= "";
    @XmlElement private String machineSid			= "";
    @XmlElement private String osName				= "";
    @XmlElement private String osArchitecture		= "";
    @XmlElement private String osSerialNumber		= "";
    @XmlElement private String cpuProcessorId			= "";
    @XmlElement private String baseboardSerialNuber	= "";
    @XmlElement private String biosSerialNuber		= "";
    @XmlElement private String clientName			= "";
    @XmlElement private String clientAddress		= "";
    @XmlElement private String clientContactNumber	= "";
    
    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getHostname()
    {
        return hostname;
    }

    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    public String getMachineName()
    {
        return machineName;
    }

    public void setMachineName(String machineName)
    {
        this.machineName = machineName;
    }

    public String getMachineSid()
    {
        return machineSid;
    }

    public void setMachineSid(String machineSid)
    {
        this.machineSid = machineSid;
    }

    public String getOsName()
    {
        return osName;
    }

    public void setOsName(String osName)
    {
        this.osName = osName;
    }

    public String getOsArchitecture()
    {
        return osArchitecture;
    }

    public void setOsArchitecture(String osArchitecture)
    {
        this.osArchitecture = osArchitecture;
    }

    public String getOsSerialNumber()
    {
        return osSerialNumber;
    }

    public void setOsSerialNumber(String osSerialNumber)
    {
        this.osSerialNumber = osSerialNumber;
    }

    public String getCpuProcessorId()
    {
        return cpuProcessorId;
    }

    public void setCpuProcessorId(String processorId)
    {
        this.cpuProcessorId = processorId;
    }

    public String getBaseboardSerialNuber()
    {
        return baseboardSerialNuber;
    }

    public void setBaseboardSerialNuber(String baseboardSerialNuber)
    {
        this.baseboardSerialNuber = baseboardSerialNuber;
    }

    public String getBiosSerialNuber()
    {
        return biosSerialNuber;
    }

    public void setBiosSerialNuber(String biosSerialNuber)
    {
        this.biosSerialNuber = biosSerialNuber;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientAddress()
    {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress)
    {
        this.clientAddress = clientAddress;
    }

    public String getClientContactNumber()
    {
        return clientContactNumber;
    }

    public void setClientContactNumber(String clientContactNumber)
    {
        this.clientContactNumber = clientContactNumber;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
}
