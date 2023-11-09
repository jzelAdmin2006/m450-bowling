# m450-bowling

## Testfall 1

**Beschreibung**: Man macht einen Strike im ersten Frame, der Einfluss auf die Punkte der nächsten Würfe hat.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Strike (10 Pins) schiessen
- Ergebnis anschauen (Frames Übersicht und Total Punkte)
- Nächstes Frame: 5 Pins, dann 3 Pins
- Ergebnis anschauen (Frames Übersicht und Total Punkte)
**Erwartetes Ergebnis**:
- Man bekommt nach dem ersten Wurf (dem Strike) zunächst nur 10 Punkte. Sobald die nächsten beiden Würfe hinzukommen, bekommt man aber jeweils für den ersten Frame die entsprechenden Punkte dazu.
- Der Strike wird in der Frame Übersicht mit einem X dargestellt, beim nächsten Wurf wird ein neuer Frame gestartet.
- Für den ersten Frame bekommt man am Ende 18 Punkte, für den zweiten bekommt man 8.
- Der totale Score beträgt am Ende 26.
