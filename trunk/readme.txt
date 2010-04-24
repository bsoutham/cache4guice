Required libraries:
	The dependencies required for cache4guice depend on which cache you are using.
	
	Cache4Guice: - simple internal cache
		 aopalliance-1.0.jar
		 guice-2.0.jar
		 logback-classic-0.9.20.jar
		 logback-core-0.9.20.jar
		 slf4j-api-1.5.11.jar
		 
	EhCache:
		everything listed under Cache4Guice (above)
		ehcache-core-2.0.1.jar
		
	Infinispan:
		everything listed under Cache4Guice (above)
		infinispan-core.jar
		jta-1.1.jar
		marshalling-api-1.2.0.GA.jar
		rhq-pluginAnnotations-1.4.0.B01.jar
		river-1.2.0.GA.jar
		
	JBossCache:
		everything listed under Cache4Guice (above)
		commons-logging.jar
		jbosscache-core.jar
		jboss-common-core.jar
		jgroups.jar