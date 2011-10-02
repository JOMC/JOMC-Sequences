// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) Christian Schulte (schulte2005@users.sourceforge.net), 2005-07-25
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
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import org.jomc.sequences.ConcurrentModificationException;
import org.jomc.sequences.SequenceExistsException;
import org.jomc.sequences.SequenceVetoException;
import org.jomc.sequences.Sequence;
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
 * Testcase for SequenceDirectory implementations.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.it.SequenceDirectoryTest>}.
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
public class SequenceDirectoryTest
{
    // SECTION-START[SequenceDirectoryTest]

    /**
     * Gets a sequence with valid data.
     *
     * @return A sequence with valid data.
     */
    public static Sequence getTestSequence()
    {
        final Sequence legal = new Sequence();
        legal.setIncrement( 1L );
        legal.setMaximum( 10L );
        legal.setMinimum( 0L );
        legal.setName( "TEST" );
        legal.setValue( 0L );

        return legal;
    }

    /**
     * Tests all {@link org.jomc.sequences.SequenceDirectory} methods to handle illegal arguments correctly by throwing
     * a {@code SequencesSystemException} with non-null message.
     */
    @Test public void testIllegalArguments() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        try
        {
            this.getSequenceDirectory().addSequence( null );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence( null, 0L );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( null, 0L, null );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( "TEST", 0L, null );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( null, 0L, new Sequence() );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().getSequence( null );
            fail( "Expected SequencesSystemException not thrown." );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

    }

    /** Tests that a valid sequence can get added, edited, searched and removed. */
    @Test public void testAddEditSearchDeleteLegalSequence() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getTestSequence();

        legal = this.getSequenceDirectory().addSequence( legal );

        System.out.println( legal );

        legal.setName( "TEST2" );

        legal = this.getSequenceDirectory().editSequence( "TEST", legal.getRevision(), legal );

        System.out.println( legal );

        assertEquals( legal, this.getSequenceDirectory().getSequence( "TEST2" ) );

        final Set<Sequence> result = this.getSequenceDirectory().searchSequences( "%TEST%" );

        assertEquals( 1, result.size() );
        assertEquals( legal, result.toArray()[0] );

        this.getSequenceDirectory().deleteSequence( "TEST2", legal.getRevision() );

        assertEquals( 0, this.getSequenceDirectory().searchSequences( null ).size() );
        assertEquals( BigInteger.ZERO, this.getSequenceDirectory().getSequenceCount() );
    }

    /** Tests that a sequence cannot get edited or removed when it got changed concurrently. */
    @Test public void testConcurrentModificationException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final Sequence legal = getTestSequence();

        final Sequence sequence = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().editSequence( "TEST", sequence.getRevision() + 1L, sequence );
            fail( "Expected ConcurrentModificationException not thrown." );
        }
        catch ( final ConcurrentModificationException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence( "TEST", sequence.getRevision() + 1L );
            fail( "Expected ConcurrentModificationException not thrown." );
        }
        catch ( final ConcurrentModificationException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    /**
     * Tests that adding an illegal sequence or updating an existing sequence with illegal data is prevented by throwing
     * a corresponding {@code SequenceVetoException}.
     */
    @Test public void testSequenceVetoException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getTestSequence();

        final Sequence illegal = new Sequence();
        char[] name = new char[ this.getSequenceNameMaxLength() + 1 ];
        Arrays.fill( name, 'T' );

        illegal.setName( String.valueOf( name ) );
        illegal.setMinimum( 100L );
        illegal.setMaximum( 1L );
        illegal.setValue( 0L );
        illegal.setIncrement( -1L );

        try
        {
            this.getSequenceDirectory().addSequence( illegal );
            fail( "Expected SequenceVetoException not thrown." );
        }
        catch ( final SequenceVetoException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        illegal.setName( null );

        try
        {
            this.getSequenceDirectory().addSequence( illegal );
            fail( "Expected SequenceVetoException not thrown." );
        }
        catch ( final SequenceVetoException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        if ( this.getSequenceNameMinLength() - 1 >= 0 )
        {
            try
            {
                name = new char[ this.getSequenceNameMinLength() - 1 ];
                Arrays.fill( name, 'T' );
                illegal.setName( String.valueOf( name ) );
                this.getSequenceDirectory().addSequence( illegal );
                fail( "Expected SequenceVetoException not thrown." );
            }
            catch ( final SequenceVetoException e )
            {
                assertNotNull( e.getMessage() );
                System.out.println( e.toString() );
            }
        }

        legal = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().editSequence( legal.getName(), legal.getRevision(), illegal );
            fail( "Expected SequenceVetoException not thrown." );
        }
        catch ( final SequenceVetoException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.getSequenceDirectory().deleteSequence( legal.getName(), legal.getRevision() );
    }

    /**
     * Tests that adding a sequence twice is prevented by throwing a corresponding {@code SequenceExistsException}.
     */
    @Test public void testSequenceExistsException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getTestSequence();

        legal = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().addSequence( legal );
            fail( "Expected SequenceExistsException not thrown." );
        }
        catch ( final SequenceExistsException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    /**
     * Tests that updating or deleting an unknown sequence is prevented by throwing a corresponding
     * {@code SequenceNotFoundException}.
     */
    @Test public void testSequenceNotFoundException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final Sequence legal = getTestSequence();

        try
        {
            this.getSequenceDirectory().editSequence( "UNKNOWN", 0L, legal );
            fail( "Expected SequenceNotFoundException not thrown." );
        }
        catch ( final SequenceNotFoundException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence( "UNKNOWN", 0L );
            fail( "Expected SequenceNotFoundException not thrown." );
        }
        catch ( final SequenceNotFoundException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.clearDirectory();
    }

    /** Tests that adding, editing and then removing multiple sequences leaves an empty directory. */
    @Test public void testAddEditDeleteMany() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final int count = 15;
        final List<Sequence> added = new ArrayList<Sequence>( count );
        final List<Sequence> updated = new ArrayList<Sequence>( count );
        for ( int i = 0; i < count; i++ )
        {
            final Sequence legal = getTestSequence();
            legal.setName( legal.getName() + ' ' + i );
            final Sequence a = this.getSequenceDirectory().addSequence( legal );
            added.add( a );
            System.out.println( "ADD: " + a );
        }

        for ( Sequence s : added )
        {
            final String oldName = s.getName();
            s.setName( oldName + "_UPDATED" );

            final Sequence u = this.getSequenceDirectory().editSequence( oldName, s.getRevision(), s );
            updated.add( u );

            System.out.println( "EDIT: " + u );
        }

        for ( Sequence s : updated )
        {
            final Sequence d = this.getSequenceDirectory().deleteSequence( s.getName(), s.getRevision() );
            System.out.println( "DELETE: " + d );
        }

        assertEquals( BigInteger.ZERO, this.getSequenceDirectory().getSequenceCount() );
    }

    /** Removes all sequences from the directory. */
    protected void clearDirectory() throws Exception
    {
        // Remove any test entries.
        for ( Sequence sequence : this.getSequenceDirectory().searchSequences( null ) )
        {
            System.out.println( this.getSequenceDirectory().deleteSequence(
                sequence.getName(), sequence.getRevision() ) );

        }

        assertEquals( BigInteger.ZERO, this.getSequenceDirectory().getSequenceCount() );
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
            final URL loggingProperties = SequenceDirectoryTest.class.getResource( "/logging.properties" );
            if ( loggingProperties != null )
            {
                final InputStream in = loggingProperties.openStream();
                LogManager.getLogManager().readConfiguration( in );
                in.close();
            }

            final List<String> l = new ArrayList<String>( Arrays.asList( args ) );
            l.add( 0, SequenceDirectoryTest.class.getName() );
            JUnitCore.main( l.toArray( new String[ l.size() ] ) );
        }
        catch ( final IOException e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code SequenceDirectoryTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public SequenceDirectoryTest()
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
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // <editor-fold defaultstate="collapsed" desc=" Generated Properties ">

    /**
     * Gets the value of the {@code <sequenceNameMaxLength>} property.
     * @return Maximum allowed length of a sequence name.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private int getSequenceNameMaxLength()
    {
        final java.lang.Integer _p = (java.lang.Integer) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "sequenceNameMaxLength" );
        assert _p != null : "'sequenceNameMaxLength' property not found.";
        return _p.intValue();
    }

    /**
     * Gets the value of the {@code <sequenceNameMinLength>} property.
     * @return Minimum required length of a sequence name.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private int getSequenceNameMinLength()
    {
        final java.lang.Integer _p = (java.lang.Integer) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "sequenceNameMinLength" );
        assert _p != null : "'sequenceNameMinLength' property not found.";
        return _p.intValue();
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
