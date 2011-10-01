// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions
 *   are met:
 *
 *     o Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     o Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *
 *   THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *   INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 *   AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 *   THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *   NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *   DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *   THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *   THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.sequences.it;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceLimitException;
import org.jomc.sequences.SequenceNotFoundException;
import org.jomc.sequences.SequencesSystemException;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Testcase for SequenceOperations implementations.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.it.SequenceOperationsTest>}.
 *   It does not provide any specified objects.
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.0-beta-3-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class SequenceOperationsTest
{
    // SECTION-START[SequenceOperationsTest]

    /** Increment value of the test sequence. */
    private static final int TEST_INCREMENT = 10;

    /**
     * Tests the {@link org.jomc.sequences.SequenceOperations#getNextSequenceValue(String)} and
     * {@link org.jomc.sequences.SequenceOperations#getNextSequenceValues(String,int)} methods to handle illegal
     * arguments correctly by throwing a {@code SequencesSystemException} with non-null message.
     */
    @Test public void testIllegalArguments() throws Exception
    {
        assert this.getSequenceOperations() != null;

        try
        {
            this.getSequenceOperations().getNextSequenceValue( null );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceOperations().getNextSequenceValues( null, 0 );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceOperations().getNextSequenceValues( "TEST", -1 );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

    }

    /**
     * Tests that requesting sequence values for unknown sequences is prevented by throwing a corresponding
     * {@code SequenceNotFoundException}.
     */
    @Test public void testSequenceNotFoundException() throws Exception
    {
        assert this.getSequenceOperations() != null;

        try
        {
            this.getSequenceOperations().getNextSequenceValue( "UNKNOWN" );
            fail( "Expected SequenceNotFoundException not thrown." );
        }
        catch ( final SequenceNotFoundException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceOperations().getNextSequenceValues( "UNKNOWN", 100 );
            fail( "Expected SequenceNotFoundException not thrown." );
        }
        catch ( final SequenceNotFoundException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    /**
     * Tests the {@link org.jomc.sequences.SequenceOperations#getNextSequenceValue(java.lang.String) } and
     * {@link org.jomc.sequences.SequenceOperations#getNextSequenceValues(java.lang.String, int) } methods to throw a
     * {@code SequenceLimitException} when a maximum value is reached.
     */
    @Test public void testSequenceLimitException() throws Exception
    {
        this.setupTestSequence();

        final long nextValue = this.getSequenceOperations().getNextSequenceValue( this.getClass().getName() );

        assertEquals( 10, nextValue );

        try
        {
            this.getSequenceOperations().getNextSequenceValue( this.getClass().getName() );
            fail( "Expected SequenceLimitException not thrown." );
        }
        catch ( final SequenceLimitException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.setupTestSequence();

        final long[] nextValues = this.getSequenceOperations().getNextSequenceValues( this.getClass().getName(), 1 );
        assertEquals( 1, nextValues.length );
        assertEquals( 10, nextValues[0] );

        try
        {
            this.getSequenceOperations().getNextSequenceValues( this.getClass().getName(), 1 );
            fail( "Expected SequenceLimitException not thrown." );
        }
        catch ( final SequenceLimitException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    /**
     * Test runner entry point.
     * <p>This method sets up the JDK's {@code LogManager} with properties found at classpath location
     * {@code "/logging.properties"} and executes {@link JUnitCore#main} passing the given arguments with this classes
     * name prepended.</p>
     *
     * @param args Command line arguments.
     */
    public static void main( final String... args )
    {
        try
        {
            final URL loggingProperties = SequenceOperationsTest.class.getResource( "/logging.properties" );
            if ( loggingProperties != null )
            {
                final InputStream in = loggingProperties.openStream();
                LogManager.getLogManager().readConfiguration( in );
                in.close();
            }

            final List<String> l = new ArrayList<String>( Arrays.asList( args ) );
            l.add( 0, SequenceOperationsTest.class.getName() );
            JUnitCore.main( l.toArray( new String[ l.size() ] ) );
        }
        catch ( final IOException e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }
    }

    private void setupTestSequence() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        Sequence s = this.getSequenceDirectory().getSequence( this.getClass().getName() );

        if ( s == null )
        {
            s = new Sequence();
            s.setIncrement( TEST_INCREMENT );
            s.setMaximum( TEST_INCREMENT );
            s.setMinimum( 0 );
            s.setName( this.getClass().getName() );
            s.setValue( 0 );

            this.getSequenceDirectory().addSequence( s );
        }
        else
        {
            s.setValue( 0 );
            this.getSequenceDirectory().editSequence( s.getName(), s.getRevision(), s );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code SequenceOperationsTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public SequenceOperationsTest()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // <editor-fold defaultstate="collapsed" desc=" Generated Dependencies ">

    /**
     * Gets the {@code <SequenceDirectory>} dependency.
     * <p>
     *   This method returns any available object of the {@code <org.jomc.sequences.SequenceDirectory>} specification at specification level 1.0.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <SequenceDirectory>} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.sequences.SequenceDirectory getSequenceDirectory()
    {
        return (org.jomc.sequences.SequenceDirectory) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SequenceDirectory" );
    }

    /**
     * Gets the {@code <SequenceOperations>} dependency.
     * <p>
     *   This method returns any available object of the {@code <org.jomc.sequences.SequenceOperations>} specification at specification level 1.0.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <SequenceOperations>} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.sequences.SequenceOperations getSequenceOperations()
    {
        return (org.jomc.sequences.SequenceOperations) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SequenceOperations" );
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
