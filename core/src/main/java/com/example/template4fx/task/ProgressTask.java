package com.example.template4fx.task;

import javafx.concurrent.Task;

public class ProgressTask
    extends Task<Void>
{
    private static final int TOTAL_WORK = 100;

    private final int duration;

    public ProgressTask( int duration )
    {
        this.duration = duration;
    }

    @Override
    protected Void call()
        throws Exception
    {
        this.updateMessage( "Starting" );
        this.updateProgress( 0, TOTAL_WORK );

        Thread.sleep( 500 );
        int unit = Math.max( 10, ( duration * 1000 ) / 100 );

        for ( int i = 1; i <= TOTAL_WORK; i++ )
        {
            this.updateMessage( String.format( "%d%% complete", i ) );
            this.updateProgress( i, TOTAL_WORK );
            Thread.sleep( unit );
        }

        return null;
    }
}
