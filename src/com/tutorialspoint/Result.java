package com.tutorialspoint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties (ignoreUnknown = true)
@JsonInclude (JsonInclude.Include.NON_NULL)
@XmlRootElement (name = "Result")
public class Result
{

    private User User;

    private String age;

    public User getUser ()
    {
        return User;
    }

    public void setUser (User User)
    {
        this.User = User;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [User = "+User+", age = "+age+"]";
    }
}
			
			