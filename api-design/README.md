#  coding-challenge api-design

## Faktensammlung

Faktensammlung aus dem gegebenen Text:

- Es gibt einen Anbieter von Schulungen.
- Es gibt Kunden, welche Schulungen buchen können.
- Eine Schulung kann mehrere Termine haben.
- Ein Termin wird genau einer Schulung zugeordnet.
- Maximal 10 Teilnehmer dürfen einen Termin buchen.

## Modellierung

Modellierung aus der Faktensammlung

Entitäten:

- Schulung (Name, Beschreibung, Preis, Referent)
- Termin (Zeitraum, maximale Teilnehmerzahl)
- Teilnehmer (Name, e-Mail)

Beziehungen:

- Eine Schulung kann 0..n Termine haben.
- Ein Termin gehört genau 1 Schulung.

      Schulung 1 --- n Termin

- Ein Teilnehmer kann mehrere Termine buchen.
- Ein Termin kann mehere Teilnehmer haben.

      Teilnehmer m --- n Termin

## Übersetzungen

Hier folgen die Übersetzungen für das zu bestimmende API-Design

Schulung - training

Termin   - appointment

Teilnehmer - participant
