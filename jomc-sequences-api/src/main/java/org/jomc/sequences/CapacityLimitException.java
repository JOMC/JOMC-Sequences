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
package org.jomc.sequences;

import java.math.BigInteger;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Gets thrown whenever the directories capacity limit has been reached.
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="2" nowrap><font size="+2">Dependencies</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Name</b></td>
 *       <td align="left" scope="col" nowrap><b>Description</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@link #getLocale Locale}</td>
 *       <td align="left">Dependency on {@code 'java.util.Locale'} {@code (java.util.Locale)} at specification level 1.1 bound to an instance.</td>
 *     </tr>
 *   </table>
 * </p>
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="3" nowrap><font size="+2">Messages</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Name</b></td>
 *       <td align="left" scope="col" nowrap><b>Languages</b></td>
 *       <td align="left" scope="col" nowrap><b>Default Template</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getCapacityLimitMessage capacityLimitMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default),&nbsp;Deutsch</td>
 *       <td align="left" valign="top" nowrap><pre><code>The capacity limit {0,number} of the sequence directory has been reached.</code></pre></td>
 *     </tr>
 *   </table>
 * </p>
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
public class CapacityLimitException extends SequencesException
{
    // SECTION-START[CapacityLimitException]

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = 5107026746069981206L;

    /**
     * Capacity limit value.
     * @serial
     */
    private final BigInteger capacityLimit;

    /**
     * Creates a new {@code CapacityLimitException} taking the capacity limit value.
     *
     * @param capacityLimit The capacity limit to report.
     */
    public CapacityLimitException( final BigInteger capacityLimit )
    {
        super();
        this.capacityLimit = capacityLimit;
    }

    /**
     * Gets the reported capacity limit.
     *
     * @return The reported capacity limit.
     */
    public BigInteger getCapacityLimit()
    {
        return this.capacityLimit;
    }

    /**
     * Gets the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage()
    {
        return this.getCapacityLimitMessage( this.getLocale(), this.getCapacityLimit() );
    }

    // SECTION-END
    // SECTION-START[Dependencies]
    // <editor-fold defaultstate="collapsed" desc=" Generated Dependencies ">

    /**
     * Gets the {@code Locale} dependency.
     * <p>This method returns the {@code 'default'} object of the {@code 'java.util.Locale'} {@code (java.util.Locale)} specification at specification level 1.1.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.util.Locale getLocale()
    {
        final java.util.Locale _d = (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "Locale" );
        assert _d != null : "'Locale' dependency not found.";
        return _d;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // <editor-fold defaultstate="collapsed" desc=" Generated Messages ">

    /**
     * Gets the text of the {@code capacityLimitMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>The capacity limit {0,number} of the sequence directory has been reached.</code></pre></td>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>Deutsch</td>
     *       <td align="left" valign="top" nowrap><pre><code>Kapazit&auml;tsgrenze {0,number} des Sequenz-Verzeichnisses erreicht.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param limit Format argument.
     * @return The text of the {@code capacityLimitMessage} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getCapacityLimitMessage( final java.util.Locale locale, final java.lang.Number limit )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "capacityLimitMessage", locale, limit );
        assert _m != null : "'capacityLimitMessage' message not found.";
        return _m;
    }
    // </editor-fold>
    // SECTION-END
}
