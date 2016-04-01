/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swisscom.bix.training;
import java.util.ArrayList;
import net.sf.jweather.metar.Metar;
import net.sf.jweather.metar.MetarParseException;
import static net.sf.jweather.metar.MetarParser.parseReport;
import org.apache.oro.text.perl.Perl5Util;

/**
 *
 * @author alex
 */
public class AsosMetarParser extends net.sf.jweather.metar.MetarParser{

           
    public static HistoricMetar parseAsosRecord(String metarData)
            throws MetarParseException {
        String dateString = null;
        String metarString = null;
        ArrayList splitData = new ArrayList();
        Perl5Util utility = new Perl5Util();

        // split the two lines of raw metar data apart
        utility.split(splitData, "/ 5-MIN /", metarData);

        dateString = (String) splitData.get(0);
        metarString = (String) splitData.get(1);
        //if (! metarString.endsWith(" $")) throw new MetarParseException("No terminator $ found. Bad sign!");
        Metar report = parseReport(metarString.trim());
        HistoricMetar result = new HistoricMetar( report );
        
        result.setLocalDateString(dateString);
        return result;
    }
    
}
