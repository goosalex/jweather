/*
jWeather(TM) is a Java library for parsing raw weather data
Copyright (C) 2004 David Castro

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

For more information, please email arimus@users.sourceforge.net
*/
package com.swisscom.bix.training;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertTrue;
import net.sf.jweather.*;
import net.sf.jweather.metar.*;



/* Test mit einem fixen String */

public class TestAsos {
	//static String station = "KLAX";
        
        
        /*"
63870K0J4 0J420080508045512205/08/08 04:55:31  5-MIN K0J4 080955Z AUTO 18005KT 5SM BR OVC004 21/20 A2990 340 93 1200 180/05 RMK AO2 SLP120 T02110200 $
    
    
	; */
        static String example = "KDRA 010515Z AUTO 00000KT 10SM CLR:31" ;//53147KINS INS20080829105514908/29/08 10:55:31  5-MIN KINS 291755Z AUTO VRB04KT 10SM CLR:20:31  5-MIN KINS 291820Z AUTO 00000KT 10SM CLR 35/11 A2990 3150 23 6100 000/00 RMK AO2 $";
	static HistoricMetar metar = null;

	public static void main(String[] args)  {
            try {    
                if ((args.length > 0) && (args[0].length() > 4)) {
                    String station = args[0];
                    metar = AsosMetarParser.parseAsosRecord(station);
                } else if (args.length > 0) {
                    System.out.println("please full Record, not only a valid station code (e.g. KCNO)");
                } else {
                    metar = AsosMetarParser.parseAsosRecord(example);
                }
            
            } catch (MetarParseException metarParseException) {
                System.out.println("com.swisscom.bix.training.TestAsos.main()");
                System.err.println("Parsing Fehler: " + metarParseException.getMessage());
            } catch (Exception e) {
                System.out.println("com.swisscom.bix.training.TestAsos.main()");
                System.err.println("Irgendein Fehler: " + e.getMessage() + "\n");
                e.printStackTrace();
            }
            System.out.println("Reultat (Report time):" + metar.report.getDate().toString().trim());
            System.out.println("Reultat (Local  time):" + metar.localDate.toString());
            metar.report.print();

            System.out.println("Wind: " + metar.report.getWindSpeedInMPS() );
            //metar.report.
	}
        
  public static void testIsSerializable(Metar m) 
   throws  IOException {

    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(m);
    oos.close();
    assertTrue(out.toByteArray().length > 0);

}
        
        
        
}
