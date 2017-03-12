package se.simplistics.template4fx;

import java.net.URL;

public class StyleSheets
{
    /**
     * Available theme names:
     * <ul>
     * <li>template4fx-light</li>
     * <li>template4fx-dark</li>
     * </ul>
     *
     * @param name the name of the predefined css theme
     * @return the string url of the predefined css theme
     */
    public static String getTheme( String name )
    {
        URL resource = StyleSheets.class.getResource( String.format( "/css/%s.css", name ) );

        if ( resource == null )
            throw new IllegalArgumentException( String.format( "Theme <%s> is not defined", name ) );

        return resource.toExternalForm();
    }
}
