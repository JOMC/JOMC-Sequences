// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2009 - 2011 The JOMC Project
 *   Copyright (c) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
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
 *   THIS SOFTWARE IS PROVIDED BY THE JOMC PROJECT AND CONTRIBUTORS "AS IS"
 *   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE JOMC PROJECT OR
 *   CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *   OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *   WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *   OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *   ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $Id$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.sequences.test;

import java.io.ObjectInputStream;
import java.net.URL;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceChangeEvent;
import org.jomc.sequences.SequenceVetoException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for class {@code org.jomc.sequences.SequenceVetoException}.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class SequenceVetoExceptionTest
{
    // SECTION-START[SequenceVetoExceptionTest]

    @Test public void testSerializable() throws Exception
    {
        final URL ser = this.getClass().getResource( "SequenceVetoException.ser" );
        assertNotNull( ser );

        final ObjectInputStream in = new ObjectInputStream( ser.openStream() );
        final SequenceVetoException e = (SequenceVetoException) in.readObject();
        in.close();

        final SequenceChangeEvent sequenceChange = e.getSequenceChangeEvent();
        assertNotNull( sequenceChange );

        assertNotNull( sequenceChange.getOldSequence() );
        assertEquals( 0L, sequenceChange.getOldSequence().getDate() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getIncrement() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getMaximum() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getMinimum() );
        assertEquals( "Sequence 1", sequenceChange.getOldSequence().getName() );
        assertEquals( 0L, sequenceChange.getOldSequence().getRevision() );
        assertEquals( Long.MAX_VALUE, sequenceChange.getOldSequence().getValue() );

        assertNotNull( sequenceChange.getNewSequence() );
        assertEquals( 0L, sequenceChange.getNewSequence().getDate() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getIncrement() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getMaximum() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getMinimum() );
        assertEquals( "Sequence 2", sequenceChange.getNewSequence().getName() );
        assertEquals( 0L, sequenceChange.getNewSequence().getRevision() );
        assertEquals( Long.MIN_VALUE, sequenceChange.getNewSequence().getValue() );

        assertEquals( 2, sequenceChange.getStatus( Sequence.PROP_INCREMENT ).size() );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_INCREMENT ).contains( SequenceChangeEvent.ILLEGAL_VALUE ) );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_INCREMENT ).contains( SequenceChangeEvent.MANDATORY_VALUE ) );

        assertEquals( 2, sequenceChange.getStatus( Sequence.PROP_MAXIMUM ).size() );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_MAXIMUM ).contains( SequenceChangeEvent.ILLEGAL_VALUE ) );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_MAXIMUM ).contains( SequenceChangeEvent.MANDATORY_VALUE ) );

        assertEquals( 2, sequenceChange.getStatus( Sequence.PROP_MINIMUM ).size() );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_MINIMUM ).contains( SequenceChangeEvent.ILLEGAL_VALUE ) );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_MINIMUM ).contains( SequenceChangeEvent.MANDATORY_VALUE ) );

        assertEquals( 2, sequenceChange.getStatus( Sequence.PROP_NAME ).size() );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_NAME ).contains( SequenceChangeEvent.ILLEGAL_VALUE ) );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_NAME ).contains( SequenceChangeEvent.MANDATORY_VALUE ) );

        assertEquals( 2, sequenceChange.getStatus( Sequence.PROP_VALUE ).size() );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_VALUE ).contains( SequenceChangeEvent.ILLEGAL_VALUE ) );
        assertTrue( sequenceChange.getStatus( Sequence.PROP_VALUE ).contains( SequenceChangeEvent.MANDATORY_VALUE ) );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code SequenceVetoExceptionTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public SequenceVetoExceptionTest()
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
