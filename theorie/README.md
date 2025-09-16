#  coding-challenge Theorie

Hie gehe ich auf das Thema Theorie ein.


## Security

- Abfragen über Schulungen und Termine können durchaus ungesichert implementiert werden (potenzieller Kunde).
- Änderungsabfragen wie das Anlegen von Schulungen müssen vom Anbieter verwaltet und geschützt werden.
- Im bisherigen Context muss unsere Applikation keine Authentifizierung implementieren. Für die geschützten Bereiche reicht eine Autorisierung. Also hat der Anfragende das Recht (Rolle) um die gewünschte Aktion durchzuführen.
- Ich schlage eine rollen-basierte implementierung für die erforderlichen Rechte vor.
- Da wir eine ressourcen-zentrierte API definieren, werden wir hinter den Http-Methoden und Pfade die erlaubten Rollen definieren.
- Technologisch werden wir JWT(JsonWebToken) basiert die Rollen anhand der Claims für unsere Applikation ableiten. Der Authentifizierungsdienst muss uns bekannt sein und dem Endnutzer die JWT bereitstellen.
- Für weitere Sicherheit werden wir das Frontend und Backend als Clients beim Authentifizierungsserver registrieren.

Beachte: Offene Schnittstellen müssen vor Attacken (Brute Force) geschützt werden, ggf. kann das zu einem Denial of Service führen.


## CI/CD

Im Vorfeld möchte ich die Gitflow mit (develop, main, feature, hotfix) definieren.
Sowohl backend und frontend werde ich beim CI/CD möglichst gleich behandeln.
Ich möchte möglichst viele automatische build-pipelines definieren, um frühzeitig Probleme zurückgespielt zu bekommen.

- Baue immer develop, main, feature und hotfix branches bei Codeänderungen.
- Merge immer im build Prozess feature in develop und prüfe die Integrität (Continuous Integration).
- Merge immer im build Prozess hotfix in main und prüfe die Integrität (Continuous Integration).
- Der develop und main build Prozess soll immer Unittests und Integrationstests durchführen.
- Entstandene Artefakte aus develop und main sollen ins Repository-Archiv. 
- Der feature and hotfix build Prozess soll immer Unittests durchführen.
- Artefakte aus develop und main sollen automatisch auf die Entwickler (develop) und Test-Umgebung (main) deployt werden (Continuous Delivery)
- Die Version soll per manuelle Freigabe auf Knopfdruck auf die produktive Umgebung mit vorgefertigten Scripten ermöglicht werden.
- Ebenso muss per Knopfdruck ein Rollback ermöglicht werden.

Ich kenne

- bamboo
- Jenkins
- gitlab CI/CD
- github Actions

Je nach Technologie sind die pipelines verschieden geartet, bis hin zu Pipeline as Code.


## Cloud

- Schalte einen API-Gateway als zentrale Einstiegsstelle für Caching Möglichkeit, Limitierung von Zugriffen.
- Lege ein Load-Balancer ab um zu entscheiden wie Anfragen an eine Service-Gruppe verteilt werden soll.
- Lege ein Kubernetes Cluster an um bei starker Last, ggf weitere Pods vom Service bereitstellen zu können.
- Lege eine relationale Datenbank an (managed).

- Eine Skalierbarkeit können wir mit einem weiteren Pod ermöglichen. Wir müssen dann in Geschäftslogik darauf achten, dass Jobs nur einmal laufen.


## Code Quality

- Das Frontend und Backend sollen für sich selbst über Unit und Integrationstests (interne Schichten testen) verfügen.
- Das Backend soll über DB nahe Tests verfügen (z.B. unique constraints, delete cascades)
- Um Sicherzustellen, dass das Gesamtsystem korrekt funktioniert muss ein EndToEnd Projekt dies prüfen. Dieses Projekt kann täglich gegen die stabile Test-Umgebung konfiguriert und ausgeführt werden.
- Sonarqube für statische Code-Analyse hat gute Darstellungen und zeigt Tendenzen sowie Abhängigkeiten an.
- Sonarqube Integration in IDEA zeigt sofort noch vor dem Commit Schwachstellen. Ggf. definieren wir eigenen Unternehmensregeln um eine höhere Qualität zu erzwingen.
- Der Prozess des Code-Reviews hilft zur guten Strukturierung und Lesbarkeit bei.
- Integration von OWASP hilft, um Schwachstellen von verwendeter Software sichtbar zu machen und zu fixen.


## Monitoring & Logging

- Einbau von health checks (bei Spring actuator) um Applikationsstatus zu kennen.
- Einbau von sinnvollen Logs überhaupt.
- Einbau von Http-Status-Logs in der Applikation für jeden Request mit Dauer (/api/v1/training  GET  20ms).
- Weitergabe der Logs an ELK-Stack oder ähnlich.
- Überwachung des Java Backends mit JVM und actuator-Integration in Datadog oder ähnlich.
- Überwachung des Frontends ist mir sentry bekannt.
- Alerting von Health-check, wenn beim vierten Versuch in Folge kein OK kommt. Jeder Versuch wird alle 30 sec durchgeführt. Alerts können in einen Chat Kanal weitergegeben werden.
- Alerting von 500-er Status sofort.
- Verschiedene Alerts auf der VM selbst, wie z.B. Festplattenplatz.
- Bei der Verwendung von vielen Microservices werden wir das Tracing integrieren.
- Neben Alerts soll eine positive Darstellung von Buchungen heute, diese Woche und diesen Monat erfolgen.

