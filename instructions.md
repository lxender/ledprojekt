## Wie man das Repo verwendet
Am einfachsten geht das direkt über IntelliJ IDEA:

Note: bei Problemen mit IntelliJ (zB. Game wird nicht als Einstiegspunkt erkannt) einfach redownloaden.

0. mach dir einen GitHub Account
1. öffne IntelliJ und wähl "Check out from Version Control"
2. wähl GitHub
3. log dich ein oder erstell nen Account
4. gib als URL das ein: ```https://github.com/lxender/ledprojekt.git```
5. wähl den Speicherort und klick "Clone"
6. auf die Frage "Would you like to create an IDEA project from it?" oder so, klick "Yes"
7. wähl "Create project from existing sources"
8. wähl "Next" (du müsstest "ledprojekt"; den Speicherort, den du gewählt hast; ".idea" sehen)
9. ".../src" müsste ausgewählt sein, wenn ja: klick "Next"
10. sollte leer sein, klick "Next"
11. Sollte "ledprojekt" ausgewählt haben, "Next"
12. "1.8" sollte angezeigt bzw. ausgewählt sein, "Next"
13. "No frameworks detected", klick auf "Finish"

### 2ter Teil: das LED-Projekt SDK
14. lad die Dateien für das LED-Projekt von Moodle herunter
15. geh auf "File" -> "Project Structure"
16. klick links auf "Modules", dann wähl "ledprojekt" -> "Dependencies"
17. klick auf das Plus unten links und wähl "JARs or directories"
18. navigier zu deinem Speicherort von "LEDBoard – Bibliothek und Anleitung" und wähl "LEDControl....jar"
19. klick "Apply"
20. teste, ob du's richtig eingebunden hast, indem du auf "Game.java" bzw. "Game" gehst und auf den grünen Pfeil klickst, der irgendwo auftauchen wird