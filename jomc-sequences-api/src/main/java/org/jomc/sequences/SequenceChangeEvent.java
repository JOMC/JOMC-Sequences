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
 *   $Id$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.sequences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * A &quot;SequenceChange&quot; event gets delivered whenever the state of a sequence changes.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.SequenceChangeEvent>}.
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
public class SequenceChangeEvent extends EventObject
{
    // SECTION-START[SequenceChangeEvent]

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = 3680953665033263363L;

    /**
     * The entity getting changed.
     * @serial
     */
    private Sequence oldSequence;

    /**
     * The entity the old sequence is changed to.
     * @serial
     */
    private Sequence newSequence;

    /**
     * The status of the event.
     * @serial
     */
    private Map<String, List<SequenceChangeStatus>> status;

    /**
     * Creates a new {@code SequenceChangeEvent} instance.
     *
     * @param source The source of the event.
     * @param oldSequence The entity getting changed or {@code null} if {@code newSequence} is about to be added to
     * {@code source}.
     * @param newSequence The value {@code oldSequence} will be changed to or {@code null} if {@code oldSequence} is
     * about to be removed from {@code source}.
     */
    public SequenceChangeEvent( final Object source, final Sequence oldSequence, final Sequence newSequence )
    {
        super( source );
        this.oldSequence = oldSequence;
        this.newSequence = newSequence;
    }

    /**
     * Gets the entity getting changed.
     *
     * @return The entity getting changed or {@code null} if a new sequence is added to the source of the event.
     */
    public final Sequence getOldSequence()
    {
        return this.oldSequence;
    }

    /**
     * Gets the entity the old sequence is changed to.
     *
     * @return The entity the old sequence is changed to or {@code null} if the old sequence is removed from the source
     * of the event.
     */
    public final Sequence getNewSequence()
    {
        return this.newSequence;
    }

    /**
     * Gets a set of all status keys of the instance.
     *
     * @return An unmodifiable set of all status keys of the instance.
     */
    public final Set<String> getStatusKeys()
    {
        return this.status == null
               ? Collections.<String>emptySet()
               : Collections.unmodifiableSet( this.status.keySet() );

    }

    /**
     * Gets status for a given key and type.
     *
     * @param key The key of the status to return.
     * @param type The class of the type of status to return.
     * @param <T> The type of status to return.
     *
     * @return An unmodifiable list of all status for {@code key} of type {@code T}.
     *
     * @throws NullPointerException if {@code type} is {@code null}.
     *
     * @see Sequence#PROP_NAME Sequence.PROP_XYZ
     * @see #getStatus(java.lang.String)
     */
    public final <T extends SequenceChangeStatus> List<T> getStatus( final String key, final Class<T> type )
    {
        if ( type == null )
        {
            throw new NullPointerException( "type" );
        }

        List<T> found = null;
        final List<SequenceChangeStatus> list = this.status != null ? this.status.get( key ) : null;

        if ( list != null )
        {
            found = new ArrayList<T>( list.size() );

            for ( SequenceChangeStatus s : list )
            {
                if ( s.getClass() == type )
                {
                    found.add( type.cast( s ) );
                }
            }
        }

        return Collections.unmodifiableList( found );
    }

    /**
     * Gets status for a given key.
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make
     * to the returned list will be present inside the object. This is why there is no {@code set} method for the
     * status property.</p>
     *
     * @param key The key of the status to return.
     *
     * @return The status for {@code key}.
     *
     * @see Sequence#PROP_NAME Sequence.PROP_XYZ
     */
    public final List<SequenceChangeStatus> getStatus( final String key )
    {
        if ( this.status == null )
        {
            this.status = new HashMap<String, List<SequenceChangeStatus>>();
        }

        List<SequenceChangeStatus> list = this.status.get( key );
        if ( list == null )
        {
            list = new LinkedList<SequenceChangeStatus>();
            this.status.put( key, list );
        }

        return list;
    }

    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
