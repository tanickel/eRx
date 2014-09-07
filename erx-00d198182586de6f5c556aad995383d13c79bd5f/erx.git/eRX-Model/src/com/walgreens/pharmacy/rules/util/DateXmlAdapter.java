package com.walgreens.pharmacy.rules.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateXmlAdapter
    extends XmlAdapter<String, Date>
{
    //private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Date unmarshal(String value) {
        return DatatypeConverter.parseDate(value).getTime();
    }

    public String marshal(Date value) {
        if ( value == null ) return null;
        return sdf.format(value);
    }
}
