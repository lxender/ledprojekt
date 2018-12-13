# ledprojekt — WIP-Name: Stickman Fight

<hr>

Wenn ihr Bock habt ein Modell grafisch zu erstellen, aber kein Bock auf Paint oder PS habt, könnt ihr dieses Tool nutzen:
https://codepen.io/lxender/full/gQZYqw

<hr>

## TODOS:
- State für easy Management der UI
- app.ledprojekt.Main einfügen, damit State eingebaut werden kann

- Schwerkraft
- Kollision

- Bounding Boxes um Charaktere?

- Lebensanzeige
    - als eigene Klasse, die Charakteren im Konstruktor hinzugefügt wird

- Schrift
    - Buchstaben (Letter.java) wrapped in Worten (Word.java)
    - basieren auf Modelle
        - *keine* Bilder, Arrays sind einfacher und schneller

- Hindernisse (bzw. Blocks oder so):
    - für Böden, Wände, etc.
    - undurchdringlich und durchdringlich

- app.ledprojekt.Model.java:
    - Rotation
    - vertikale Spiegelung

- "Facing Directions"

- Animationen
    - Animationen mit Keyframes
    - als Klasse, die man Modellen hinzufügen kann
    - besteht selbst aus Modellen

- modulare Abilities (wie Springen, Schlagen, Laufen, Ducken, etc.)
    - modular, um sie ein- und ausschalten zu können

- Menü?
    - Charakter-Auswahl
    - Ability-Auswahl?
