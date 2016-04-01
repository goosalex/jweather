/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swisscom.bix.training;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.jweather.metar.Metar;
import net.sf.jweather.metar.MetarParseException;
/**
 *
 * @author alex
 */
public class HistoricMetar implements Serializable{
    
    private static SimpleDateFormat sdf = new SimpleDateFormat(
//  63870K0J4 0J420080531235512705/31/08 23:55:31  5-MIN K0J4 010455Z AUTO 22003KT 10SM CLR 24/21 A3006 190 81 1300 220/03 RMK AO2 SLP175 T02390206 403330206 ";
              "yyyyMMddHHmm"
        );
    
    public Date   localDate;
    public String metadataString;
    public Metar  report;
    public HistoricMetar(Metar report) {
        this.report = report;
    }
    
    public void setLocalDate(Date pastDate) {
        this.localDate = pastDate;
    }
    
    public void setLocalDateString(String pastDateString) throws MetarParseException {
        this.metadataString = pastDateString;
        Date result = null;        
        try {
            String input = pastDateString.trim().substring(13, 25);
            result = sdf.parse(input);
        } catch (ParseException pExc) {
            throw new MetarParseException(pExc);
        }
        this.localDate = result;
    }
}
