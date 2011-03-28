<?xml version="1.0" encoding="UTF-8"?>
<!--

  Copyright (C) 2009 The JOMC Project
  Copyright (C) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
  All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions
  are met:

    o Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.

    o Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in
      the documentation and/or other materials provided with the
      distribution.

  THIS SOFTWARE IS PROVIDED BY THE JOMC PROJECT AND CONTRIBUTORS "AS IS"
  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE JOMC PROJECT OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

  $Id$

-->
<xsl:stylesheet xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jomc="http://jomc.org/model"
                version="1.0">

  <xsl:output method="xml" indent="yes" omit-xml-declaration="no"
              encoding="UTF-8" standalone="no"/>

  <xsl:template match="node()|@*">
    <xsl:copy>
      <xsl:apply-templates select="node()|@*"/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="jomc:implementations/jomc:implementation[@identifier = 'org.jomc.sequences.ri.DefaultSequenceDirectory']">
    <xsl:copy>
      <xsl:attribute name="location">
        <xsl:value-of select="'jndi+rmi:jomc-sequences-ear-${project.version}/org.jomc.sequences.ri.DefaultSequenceDirectory/remote'"/>
      </xsl:attribute>
      <xsl:apply-templates select="node()|@*"/>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
