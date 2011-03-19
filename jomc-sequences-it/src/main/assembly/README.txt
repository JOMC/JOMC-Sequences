
  ${project.organization.name} - ${project.name} - README.txt
  Version ${project.version} Build ${buildNumber}
  ${project.url}

  ${project.description}

  Environment

    Prior to testing, the JOMC Sequences EAR artifact needs to be deployed to
    the container and the environment variable CLASSPATH_PREFIX needs to hold
    the client classpath corresponding to the container to test.

  JBoss

    For testing against a local JBoss application server copy the file
    etc/jboss-jndi.properties to etc/jndi.properties and update the
    JNDI names in file etc/META-INF/jomc.xml accordingly.

    for i in jboss-5.1.0.GA/client/*.jar;do
      CLASSPATH_PREFIX="$i:$CLASSPATH_PREFIX"
    done
    CLASSPATH_PREFIX=jboss-5.1.0.GA/common/lib/jnpserver.jar:$CLASSPATH_PREFIX
    export CLASSPATH_PREFIX

    location="jndi+rmi:jomc-sequences-ear-${project.version}/org.jomc.sequences.ri.DefaultSequenceDirectory/remote-org.jomc.sequences.SequenceOperations"
    location="jndi+rmi:jomc-sequences-ear-${project.version}/org.jomc.sequences.ri.DefaultSequenceDirectory/remote-org.jomc.sequences.SequenceDirectory"

  Glassfish

    For testing against a local Glassfish application server copy the
    file etc/glassfish-jndi.properties to etc/jndi.properties and update
    the JNDI names in file etc/META-INF/jomc.xml accordingly.

    for i in glassfish-v2.1-b60e-linux-ml/lib/*.jar;do
      CLASSPATH_PREFIX="$i:$CLASSPATH_PREFIX"
    done
    export CLASSPATH_PREFIX

    location="jndi+rmi://#org.jomc.sequences.SequenceOperations"
    location="jndi+rmi://#org.jomc.sequences.SequenceDirectory"

  Other

    Setup of various application servers should be similar to the examples shown
    above. For sharing test setups please use one of the project mailing lists.

  JDK 1.5

    The 'lib/jdk5/ext' directory contains JDK extensions to setup using the
    'java.ext.dirs' system property or another mechanism compatible to the JDK
    in use.

    The 'lib/jdk5/endorsed' directory contains updates to libraries part of the
    JDK to setup via the 'java.endorsed.dirs' system property or another
    mechanism compatible to the JDK in use. Use of these libraries may become
    necessary when encountering problems with the XML parsers of the JDK.

    export JAVA_OPTS="-Djava.ext.dirs=lib/jdk5/ext \
                      -Djava.endorsed.dirs=lib/jdk5/endorsed"
