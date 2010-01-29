// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@jomc.org>
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
package org.jomc.sequences;

import java.util.EventListener;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Gets called whenever the state of a sequence is about to change.
 * <p>This specification declares a multiplicity of {@code Many}.
 * An application assembler may provide multiple implementations of this specification (including none).
 * Use of class {@link org.jomc.ObjectManager ObjectManager} is supported for getting these implementations or for
 * selecting a single implementation.<pre>
 * VetoableSequenceChangeListener[] objects = (VetoableSequenceChangeListener[]) ObjectManagerFactory.getObjectManager( getClassLoader() ).getObject( VetoableSequenceChangeListener.class );
 * VetoableSequenceChangeListener object = ObjectManagerFactory.getObjectManager( getClassLoader() ).getObject( VetoableSequenceChangeListener.class, "<i>implementation name</i>" );
 * </pre>
 * </p>
 *
 * <p>This specification does not apply to any scope. A new object is returned whenever requested.</p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
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
