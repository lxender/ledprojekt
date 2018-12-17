# ledprojekt — WIP-Name: Stickman Fight

<hr>

Wenn ihr Bock habt ein Modell grafisch zu erstellen, aber kein Bock auf Paint oder PS habt, könnt ihr dieses Tool nutzen:
https://codepen.io/lxender/full/gQZYqw

<hr>

## TODOS:
- State für easy Management der UI
- Main einfügen, damit State eingebaut werden kann ✅

‼️‼️‼️‼️‼️‼️‼️️‼️‼️<br>
‼️‼️- Schwerkraft <br>
‼️‼️- Kollision <br>
‼️‼️‼️‼️‼️‼️‼️‼️️‼️<br>

- Bounding Boxes um Charaktere im Debugging?

- Lebensanzeige
    - ~~als eigene Klasse, die Charakteren im Konstruktor hinzugefügt wird~~
    - als Rückgabe eines Charakters mit Leben

- Schrift ✅
    - Buchstaben (Letter.java) wrapped in Worten (Word.java) ✅
    - basieren auf Modellen ✅
        - *keine* Bilder, Arrays sind einfacher und schneller ✅

- Hindernisse (bzw. Blocks oder so):
    - für Böden, Wände, etc. ✅
    - undurchdringlich und durchdringlich

- Model.java:
    - Rotation (ist das überhaupt wichtig?)
    - vertikale Spiegelung ✅

- "Facing Directions"

- Animationen
    - Animationen mit Keyframes
    - als Klasse, die man Modellen hinzufügen kann
    - besteht selbst aus Modellen

- modulare Abilities (wie Springen, Schlagen, Laufen, Ducken, etc.) ✅
    - modular, um sie ein- und ausschalten zu können ✅

- Menü?
    - Charakter-Auswahl
    - Ability-Auswahl?
