package se.simplistics.template4fx;

import java.util.Properties;

public class CustomProperties
    extends Properties
{
    /**
     * Tries to parse a property value as an integer.
     *
     * @param key the key of the desired property
     * @return the integer value of the desired property
     * @throws NumberFormatException if the property can not be parsed as an integer
     */
    public int getInt( String key )
        throws NumberFormatException
    {
        return Integer.valueOf( getProperty( key ) );
    }
}
