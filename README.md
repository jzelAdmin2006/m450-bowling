# m450-bowling

## Testfall 1

**Beschreibung**: Man macht einen Strike im ersten Frame, der Einfluss auf die Punkte der nächsten Würfe hat.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Strike (10 Pins) schiessen.
- Ergebnis anschauen (Frames Übersicht und Total Punkte).
- Nächstes Frame: 5 Pins, dann 3 Pins.
- Ergebnis anschauen (Frames Übersicht und Total Punkte).
**Erwartetes Ergebnis**:
- Man bekommt nach dem ersten Wurf (dem Strike) zunächst nur 10 Punkte. Sobald die nächsten beiden Würfe hinzukommen, bekommt man aber jeweils für den ersten Frame die entsprechenden Punkte dazu.
- Der Strike wird in der Frame Übersicht mit einem X dargestellt, beim nächsten Wurf wird ein neuer Frame gestartet.
- Für den ersten Frame bekommt man am Ende 18 Punkte, für den zweiten bekommt man 8.
- Der totale Score beträgt am Ende 26.

## Testfall 2

**Beschreibung**: Man wirft das ganze Spiel nur Strikes.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- 12 Male einen Strike (10 Pins) schiessen.
- Versuchen, nochmals zu schiessen.
**Erwartetes Ergebnis**:
- Es werden die 12 Strikes mit 12 X dargestellt (9 davon jeweils im ersten Wurf der ersten 9 Frames, die letzten 3 im letzten Frame).
- Man hat am Schluss 300 Punkte.
- Es kommt bei einem weiteren Schuss eine entsprechende Meldung, dass das Spiel fertig ist und man wieder ein neues starten muss.

## Testfall 3

**Beschreibung**: Ungültiger Wurf mit mehr als 10 Pins.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Versuchen, 11 Pins in einem Wurf zu werfen.
**Erwartetes Ergebnis**:
- Es erscheint eine Fehlermeldung, dass man nicht mehr als 10 Pins in einem Wurf werfen kann.

## Testfall 4

**Beschreibung**: Ungültiger Wurf mit negativer Anzahl an Pins.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Versuchen, -1 Pin in einem Wurf zu werfen.
**Erwartetes Ergebnis**:
- Es erscheint eine Fehlermeldung, dass die Anzahl der Pins nicht negativ sein kann.

## Testfall 5

**Beschreibung**: Ungültige Summe der Pins in zwei Würfen eines Frames.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Im ersten Wurf 6 Pins, im zweiten Wurf 5 Pins.
**Erwartetes Ergebnis**:
- Es erscheint eine Fehlermeldung, dass die Summe der Pins in einem Frame nicht mehr als 10 sein kann.

## Testfall 6

**Beschreibung**: Zwei Gutterballs in einem Frame.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Zwei Würfe in einem Frame machen, ohne einen Pin zu treffen.
**Erwartetes Ergebnis**:
- Es wird zweimal angezeigt, dass 0 Pins getroffen wurden.
- Der Score für diesen Frame bleibt bei 0.

## Testfall 7

**Beschreibung**: Vollständiges Spiel ohne Strikes oder Spares.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- 20 Würfe durchführen, in jedem Wurf 4 Pins umwerfen.
**Erwartetes Ergebnis**:
- In jedem Frame wird ein Score von 8 (4+4) angezeigt.
- Der Gesamtscore am Ende des Spiels ist 80.

## Testfall 8

**Beschreibung**: Spiel mit wechselnden Spares und normalen Würfen.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Abwechselnd einen Spare und einen normalen Wurf (4, 4) machen.
**Erwartetes Ergebnis**:
- Bei jedem Spare wird der Score des nächsten Wurfs zum Score des Spares hinzugerechnet.
- Bei den normalen Würfen werden die Punkte normal gezählt.
- Der Gesamtscore ergibt sich aus der Summe aller Frames.

## Testfall 9

**Beschreibung**: Spiel mit abwechselnden Strikes und Gutterballs.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Abwechselnd einen Strike und zwei Gutterballs werfen.
**Erwartetes Ergebnis**:
- Nach jedem Strike werden die Punkte der nächsten zwei Würfe hinzugefügt (in diesem Fall 0).
- Die Gesamtpunktzahl berechnet sich aus der Summe aller Frames.

## Testfall 10

**Beschreibung**: Spiel mit einem Strike im letzten Frame und Bonuswürfen.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Bis zum letzten Frame normal spielen.
- Im letzten Frame einen Strike werfen und dann zwei Bonuswürfe (5 Pins und 3 Pins).
**Erwartetes Ergebnis**:
- Der Strike im letzten Frame ermöglicht zwei Bonuswürfe.
- Der Score für den letzten Frame beträgt 18 (10 + 5 + 3).
- Die Gesamtpunktzahl berechnet sich aus der Summe aller Frames inklusive der Bonuswürfe.

## Testfall 11

**Beschreibung**: Wechsel zwischen Gutterball und Strike.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Abwechselnd einen Gutterball (0 Pins) und einen Strike werfen.
**Erwartetes Ergebnis**:
- Nach jedem Gutterball bleibt der Score bei 0.
- Nach jedem Strike werden die Punkte für den Strike (10) und die folgenden zwei Würfe (hier 0 und 10) addiert.

## Testfall 12

**Beschreibung**: Durchgehend Spares werfen.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- In jedem Frame einen Spare werfen.
**Erwartetes Ergebnis**:
- Jeder Spare zeigt die Summe von 10 plus die Punkte des nächsten Wurfes.
- Der Gesamtscore wird entsprechend berechnet.

## Testfall 13

**Beschreibung**: Spiel mit einem Spare im letzten Frame und einem Bonuswurf.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Bis zum letzten Frame normal spielen.
- Im letzten Frame einen Spare werfen und dann einen Bonuswurf (6 Pins).
**Erwartetes Ergebnis**:
- Der Spare im letzten Frame ermöglicht einen Bonuswurf.
- Der Score für den letzten Frame beträgt 16 (10 + 6).
- Die Gesamtpunktzahl berechnet sich aus der Summe aller Frames.

## Testfall 14

**Beschreibung**: Spiel mit zwei Strikes in Folge und einem normalen Wurf.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Zwei Strikes in Folge werfen, gefolgt von einem normalen Wurf (4, 2).
**Erwartetes Ergebnis**:
- Jeder Strike zählt 10 Punkte plus die Punkte der nächsten zwei Würfe.
- Der Score nach den Strikes und dem normalen Wurf wird entsprechend berechnet.

## Testfall 15

**Beschreibung**: Ungültiger Wurf mit nicht-numerischer Eingabe.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist im Ursprungszustand (keine Würfe).
**Schritte**:
- Versuchen, einen Wurf mit einer nicht-numerischen Eingabe (z.B. "a") zu machen.
**Erwartetes Ergebnis**:
- Es erscheint eine Fehlermeldung, dass nur numerische Eingaben gültig sind.

## Testfall 16

**Beschreibung**: Persistierte Spiele anzeigen.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Es wurden zwei Spiele persistiert:
	- Ein "Gutter Game" (10 × 0 Pins)
	- Ein perfektes Game (12 × 10 Pins)
**Schritte**:
- Man ruft den Befehl zum Abfragen aller persistierten Spiele auf.
**Erwartetes Ergebnis**:
- Bei den beiden persistierten Spiele werden die Frames korrekt angezeigt mit 0 und X.
- Bei den beiden persistierten Spiele werden die Scores korrekt angezeigt (0 und 300).

## Testfall 17

**Beschreibung**: Das Spiel beenden, sobald es fertig ist.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist ein fertiges "Gutter Game" (10 × 0 Pins).
**Schritte**:
- Man ruft den Befehl zum Beenden auf.
**Erwartetes Ergebnis**:
- Es gibt eine Meldung, dass das Spiel beendet wurde.
- Das Spiel wurde persistiert und ist in der Liste der persistierten Spiele zu sehen.
- Der Spielstand ist wieder im Ursprungszustand (keine Würfe).

## Testfall 18

**Beschreibung**: Das Spiel beenden, wenn das Spiel noch nicht fertig ist.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist, dass bereits 11 Strikes geworfen wurden.
**Schritte**:
- Man ruft den Befehl zum Beenden auf.
**Erwartetes Ergebnis**:
- Man bekommt einen Fehler, dass das Spiel fertig sein muss, damit man es beenden kann.
- Das Spiel wurde noch nicht persistiert und ist in der Liste der persistierten Spiele nicht zu sehen.
- Der Spielstand ist immer noch derselbe.

## Testfall 19

**Beschreibung**: Den Spielstand zurücksetzen, sobald das Spiel fertig ist.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist ein fertiges "Gutter Game" (10 × 0 Pins).
**Schritte**:
- Man ruft den Befehl zum Zurücksetzen auf.
**Erwartetes Ergebnis**:
- Es gibt eine Meldung, dass der Spielstand zurückgesetzt wurde.
- Das Spiel wurde nicht persistiert und ist nicht in der Liste der persistierten Spiele zu sehen.
- Der Spielstand ist wieder im Ursprungszustand (keine Würfe).

## Testfall 20

**Beschreibung**: Man versucht, das Spiel zurückzusetzen, wenn es noch nicht fertig ist.
**Vorbedingungen**:
- Die Applikation wurde gestartet.
- Die Umgebungsvariablen wurden korrekt gesetzt.
- Der Spielstand ist, dass bereits 11 Strikes geworfen wurden.
**Schritte**:
- Man ruft den Befehl zum Zurücksetzen auf.
**Erwartetes Ergebnis**:
- Es gibt eine Meldung, dass der Spielstand zurückgesetzt wurde.
- Der Spielstand ist wieder im Ursprungszustand (keine Würfe).