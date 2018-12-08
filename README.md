# ledprojekt — WIP-Name: Stickman Fight

<hr>

Wenn ihr Bock habt ein Modell grafisch zu erstellen, aber keine Lust habt, das mit Paint oder PS zu machen könnt ihr dieses primitive Tool nutzen:
https://codepen.io/lxender/full/gQZYqw

<hr>

## TODOS:
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

- Model.java:
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
