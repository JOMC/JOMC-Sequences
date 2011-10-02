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
package org.jomc.sequences;

import java.util.EventListener;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Gets called whenever the state of a sequence is about to change.
 *
 * <p>
 *   This specification is identified by identifier {@code <org.jomc.sequences.VetoableSequenceChangeListener>}.
 *   An application assembler may provide multiple implementations of this specification (including none) due to
 *   multiplicity {@code <Many>}.
 *   This specification does not apply to any scope. A new object is returned whenever requested.
 * </p>
 *
 * <p>
 *   Use of class {@code ObjectManager} is supported for accessing implementations.
 *   <blockquote><pre>
 * VetoableSequenceChangeListener[] objects = ObjectManagerFactory.getObjectManager( getClass().getClassLoader() ).getObject( VetoableSequenceChangeListener[].class );
 * VetoableSequenceChangeListener object = ObjectManagerFactory.getObjectManager( getClass().getClassLoader() ).getObject( VetoableSequenceChangeListener.class, "<i>implementation name</i>" );
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
public interface VetoableSequenceChangeListener extends EventListener
{
    // SECTION-START[VetoableSequenceChangeListener]

    /**
     * Gets called whenever the state of a sequence is about to change.
     *
     * @param evt The event describing the change about to happen.
     *
     * @throws SequenceVetoException if the implementation chooses to prevent the change from being performed.
     * @throws SequencesSystemException if notifying fails.
     */
    void vetoableSequenceChange( SequenceChangeEvent evt ) throws SequenceVetoException, SequencesSystemException;

    // SECTION-END
}
