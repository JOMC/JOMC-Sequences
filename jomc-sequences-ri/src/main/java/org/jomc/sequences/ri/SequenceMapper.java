// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) 2009 - 2011 The JOMC Project
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
 *   $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.sequences.ri;

import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequencesSystemException;
import org.jomc.sequences.model.SequenceType;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Maps model classes.
 *
 * <p>
 *   This specification is identified by identifier {@code <org.jomc.sequences.ri.SequenceMapper>}.
 *   An application assembler may provide multiple implementations of this specification (including none) due to
 *   multiplicity {@code <Many>}.
 *   This specification does not apply to any scope. A new object is returned whenever requested.
 * </p>
 *
 * <p>
 *   Use of class {@code ObjectManager} is supported for accessing implementations.
 *   <blockquote><pre>
 * SequenceMapper[] objects = ObjectManagerFactory.getObjectManager( getClass().getClassLoader() ).getObject( SequenceMapper[].class );
 * SequenceMapper object = ObjectManagerFactory.getObjectManager( getClass().getClassLoader() ).getObject( SequenceMapper.class, "<i>implementation name</i>" );
 *   </pre></blockquote>
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.0
 * @see org.jomc.ObjectManagerFactory
 * @see org.jomc.ObjectManager
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public interface SequenceMapper
{
    // SECTION-START[SequenceMapper]

    /**
     * Maps a {@code SequenceType} instance to a {@code Sequence} instance.
     *
     * @param sequenceType The instance to map.
     * @param sequence The target instance to map {@code sequenceType} to.
     *
     * @return {@code sequenceType} mapped to {@code sequence}.
     *
     * @throws SequencesSystemException if mapping fails unexpectedly.
     */
    Sequence map( SequenceType sequenceType, Sequence sequence ) throws SequencesSystemException;

    /**
     * Maps a {@code Sequence} instance to a {@code SequenceType} instance.
     *
     * @param sequence The instance to map.
     * @param sequenceType The target instance to map {@code sequence} to.
     *
     * @return {@code sequence} mapped to {@code sequenceType}.
     *
     * @throws SequencesSystemException if mapping fails unexpectedly.
     */
    SequenceType map( Sequence sequence, SequenceType sequenceType ) throws SequencesSystemException;

    // SECTION-END
}
