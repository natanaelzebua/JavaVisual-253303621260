# TP5 — Character Manager Sprint #2
Pada sprint ini ditambahkan class CharacterManager yang mengelola koleksi Character memakai ArrayList, dengan penolakan nama duplikat lewat equals(), getAllCharacters() yang mengembalikan salinan (defensive copy), serta counter static untuk jumlah objek yang dibuat.

## Fitur baru
- equals() pada Character membandingkan nama, sehingga nama duplikat ditolak
- totalCharacterCreated (static) menghitung jumlah objek Character yang dibuat
- CharacterManager: addCharacter(), findByName(), removeCharacter(), getTotalCharacters(), getAllCharacters()
- Mini-Challenge: getAverageLevel() (mengembalikan 0 jika list kosong) dan findByType()
- Fitur mandiri: getTypeSummary()

## Screenshot output (CRUD dan eksperimen reference)