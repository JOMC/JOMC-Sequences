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
package org.jomc.sequences.test;

import org.jomc.sequences.SequenceChangeStatus;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceChangeEvent;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for class {@code org.jomc.sequences.SequenceChangeEvent}.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.test.SequenceChangeEventTest>}.
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
public class SequenceChangeEventTest
{
    // SECTION-START[SequenceChangeEventTest]

    @Test public void testSerializable() throws Exception
    {
        final URL ser = this.getClass().getResource( "/org/jomc/sequences/test/SequenceChangeEvent.ser" );
        assertNotNull( ser );

        final ObjectInputStream in = new ObjectInputStream( ser.openStream() );
        final SequenceChangeEvent sequenceChange = (SequenceChangeEvent) in.readObject();
        in.close();

        assertNotNull( sequenceChange.getOldSequence() );
        assertEquals( 0L, sequenceChange.getOldSequence().getDate() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getIncrement() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getMaximum() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getMinimum() );
        assertEquals( "Old Sequence", sequenceChange.getOldSequence().getName() );
        assertEquals( 0L, sequenceChange.getOldSequence().getRevision() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getValue() );

        assertNotNull( sequenceChange.getNewSequence() );
        assertEquals( 0L, sequenceChange.getNewSequence().getDate() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getIncrement() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getMaximum() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getMinimum() );
        assertEquals( "New Sequence", sequenceChange.getNewSequence().getName() );
        assertEquals( 0L, sequenceChange.getNewSequence().getRevision() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getValue() );

        assertInvalidNumberStatus( sequenceChange, Sequence.PROP_INCREMENT );
        assertInvalidNumberStatus( sequenceChange, Sequence.PROP_MAXIMUM );
        assertInvalidNumberStatus( sequenceChange, Sequence.PROP_MINIMUM );
        assertInvalidNumberStatus( sequenceChange, Sequence.PROP_VALUE );
        assertInvalidStringStatus( sequenceChange, Sequence.PROP_NAME );
    }

    private static void assertInvalidNumberStatus( final SequenceChangeEvent e, final String key )
    {
        assertEquals( 1, e.getStatus( key ).size() );
        assertEquals( 1, e.getStatus( key, SequenceChangeStatus.InvalidNumber.class ).size() );
        assertEquals( SequenceChangeStatus.InvalidNumber.IDENTIFIER, e.getStatus( key ).get( 0 ).getIdentifier() );

        final SequenceChangeStatus.InvalidNumber invalidNumber =
            e.getStatus( key, SequenceChangeStatus.InvalidNumber.class ).get( 0 );

        assertEquals( SequenceChangeStatus.InvalidNumber.IDENTIFIER, invalidNumber.getIdentifier() );
        assertEquals( Long.MAX_VALUE, invalidNumber.getInvalidNumber() );
        assertEquals( Long.MAX_VALUE, invalidNumber.getMaximum() );
        assertEquals( 0L, invalidNumber.getMinimum() );
        assertEquals( SequenceChangeStatus.ERROR, invalidNumber.getType() );

        System.out.println( invalidNumber );
    }

    private static void assertInvalidStringStatus( final SequenceChangeEvent e, final String key )
    {
        assertEquals( 1, e.getStatus( key ).size() );
        assertEquals( 1, e.getStatus( key, SequenceChangeStatus.InvalidString.class ).size() );
        assertEquals( SequenceChangeStatus.InvalidString.IDENTIFIER, e.getStatus( key ).get( 0 ).getIdentifier() );

        final SequenceChangeStatus.InvalidString invalidString =
            e.getStatus( key, SequenceChangeStatus.InvalidString.class ).get( 0 );

        assertEquals( SequenceChangeStatus.InvalidString.IDENTIFIER, invalidString.getIdentifier() );
        assertNotNull( invalidString.getInvalidCharacters() );
        assertEquals( 0, invalidString.getInvalidCharacters().length );
        assertEquals( "New Sequence", invalidString.getInvalidString() );
        assertEquals( Long.MAX_VALUE, invalidString.getMaximumLength() );
        assertEquals( Long.MAX_VALUE, invalidString.getMinimumLength() );
        assertEquals( SequenceChangeStatus.ERROR, invalidString.getType() );

        System.out.println( invalidString );
    }

    public static void main( final String... args ) throws Exception
    {
        final Sequence oldSequence = new Sequence();
        oldSequence.setIncrement( Long.MAX_VALUE );
        oldSequence.setMaximum( Long.MAX_VALUE );
        oldSequence.setMinimum( Long.MAX_VALUE );
        oldSequence.setName( "Old Sequence" );
        oldSequence.setValue( Long.MAX_VALUE );

        final Sequence newSequence = new Sequence();
        newSequence.setIncrement( Long.MIN_VALUE );
        newSequence.setMaximum( Long.MIN_VALUE );
        newSequence.setMinimum( Long.MIN_VALUE );
        newSequence.setName( "New Sequence" );
        newSequence.setValue( Long.MIN_VALUE );

        final SequenceChangeEvent e =
            new SequenceChangeEvent( SequenceChangeEventTest.class, oldSequence, newSequence );

        e.getStatus( Sequence.PROP_INCREMENT ).add( new SequenceChangeStatus.InvalidNumber(
            SequenceChangeStatus.ERROR, Long.MAX_VALUE, 0L, Long.MAX_VALUE ) );

        e.getStatus( Sequence.PROP_MAXIMUM ).add( new SequenceChangeStatus.InvalidNumber(
            SequenceChangeStatus.ERROR, Long.MAX_VALUE, 0L, Long.MAX_VALUE ) );

        e.getStatus( Sequence.PROP_MINIMUM ).add( new SequenceChangeStatus.InvalidNumber(
            SequenceChangeStatus.ERROR, Long.MAX_VALUE, 0L, Long.MAX_VALUE ) );

        e.getStatus( Sequence.PROP_VALUE ).add( new SequenceChangeStatus.InvalidNumber(
            SequenceChangeStatus.ERROR, Long.MAX_VALUE, 0L, Long.MAX_VALUE ) );

        e.getStatus( Sequence.PROP_NAME ).add( new SequenceChangeStatus.InvalidString(
            SequenceChangeStatus.ERROR, "New Sequence", new char[ 0 ], Long.MAX_VALUE, Long.MAX_VALUE ) );

        final ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( "SequenceChangeEvent.ser" ) );
        out.writeObject( e );
        out.close();
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code SequenceChangeEventTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public SequenceChangeEventTest()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
