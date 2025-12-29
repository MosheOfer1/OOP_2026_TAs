# מדריך מקיף לשימוש ב-Git בצורה לוקאלית בלבד

מדריך זה מציג עבודה מלאה עם Git **ברמה לוקאלית בלבד**, ללא שימוש ב-remote, push, pull או fetch. כל הדוגמאות רצות על ריפו אחד עקבי בשם `demo-repo`, וכל פקודה מוצגת כך שניתן להעתיק ולהריץ אותה ישירות.

המטרה: הבנה עמוקה של Git וניהול מלא של ריפוזיטורי לוקאלי, כולל תיקון טעויות.

---

## תוכן עניינים

1. [מושגי יסוד ומצבי Git](#1-מושגי-יסוד-ומצבי-git)
2. [יצירת ריפו ובדיקת מצב](#2-יצירת-ריפו-ובדיקת-מצב)
3. [הוספה ל-Staging ויצירת קומיטים](#3-הוספה-ל-staging-ויצירת-קומיטים)
4. [בדיקת היסטוריה: log, show, blame](#4-בדיקת-היסטוריה-log-show-blame)
5. [השוואת שינויים: diff](#5-השוואת-שינויים-diff)
6. [עבודה עם ענפים](#6-עבודה-עם-ענפים)
7. [checkout, switch, restore](#7-checkout-switch-restore)
8. [מיזוג ענפים: merge](#8-מיזוג-ענפים-merge)
9. [rebase ו-interactive rebase](#9-rebase-ו-interactive-rebase)
10. [ביטול שינויים: reset](#10-ביטול-שינויים-reset)
11. [ביטול קומיטים בצורה בטוחה: revert](#11-ביטול-קומיטים-בצורה-בטוחה-revert)
12. [stash: שמירת עבודה זמנית](#12-stash-שמירת-עבודה-זמנית)
13. [reflog: שחזור טעויות קשות](#13-reflog-שחזור-טעויות-קשות)
14. [פקודות ניהול נוספות: rm, mv, clean, tag, cherry-pick, bisect](#14-פקודות-ניהול-נוספות)
15. [טבלת סיכום פקודות](#15-טבלת-סיכום-פקודות)

---

## 1. מושגי יסוד ומצבי Git

ל-Git יש שלושה מצבים מרכזיים:

- **Working Directory**: הקבצים כפי שהם בתיקייה
- **Staging Area (Index)**: שינויים שמוכנים לקומיט הבא
- **Commit History**: היסטוריית הקומיטים

המעבר:

Working Directory -> `git add` -> Staging -> `git commit` -> History

---

## 2. יצירת ריפו ובדיקת מצב

ניצור ריפו חדש בשם `demo-repo`:

```bash
mkdir demo-repo
cd demo-repo
git init
```

בדיקת מצב:

```bash
git status
```

מצב צפוי: ריפו ריק, ללא קומיטים.

---

## 3. הוספה ל-Staging ויצירת קומיטים

ניצור קובץ ראשון:

```bash
echo "# Demo Repo" > README.md
```

בדיקת מצב:

```bash
git status
```

### git add

הוספת קובץ ל-Staging:

```bash
git add README.md
```

אפשרויות נפוצות:

- `git add .` הוספת כל השינויים
- `git add -p` הוספה אינטראקטיבית לפי חלקים

### git commit

יצירת קומיט:

```bash
git commit -m "Initial commit with README"
```

### amend

תיקון הקומיט האחרון:

```bash
echo "Local Git tutorial" >> README.md
git add README.md
git commit --amend -m "Initial commit with README and description"
```

---

## 4. בדיקת היסטוריה: log, show, blame

### git log

```bash
git log
```

שימושים נפוצים:

```bash
git log --oneline --graph --decorate
```

### git show

```bash
git show HEAD
```

### git blame

```bash
git blame README.md
```

מציג מי שינה כל שורה ומתי.

---

## 5. השוואת שינויים: diff

### Working Directory מול Staging

```bash
echo "Another line" >> README.md
git diff
```

### Staging מול Commit

```bash
git add README.md
git diff --staged
```

---

## 6. עבודה עם ענפים

### יצירה ורשימה

```bash
git branch feature-a
git branch
```

### שינוי שם

```bash
git branch -m feature-a feature-readme
```

### מחיקה

```bash
git branch -d feature-readme
```

---

## 7. checkout, switch, restore

### switch

```bash
git switch -c feature-readme
```

### checkout (ישן ורב-שימושי)

```bash
git checkout master
```

### restore

שחזור קובץ מה-Index:

```bash
git restore README.md
```

---

## 8. מיזוג ענפים: merge

ניצור שינוי בענף feature:

```bash
git switch -c feature-text
echo "Feature line" >> README.md
git add README.md
git commit -m "Add feature line"
```

נחזור ל-main ונמזג:

```bash
git switch master
git merge feature-text
```

### קונפליקטים

במקרה של קונפליקט:

1. פותחים את הקובץ
2. פותרים ידנית
3. `git add`
4. `git commit`

---

## 9. rebase ו-interactive rebase

### rebase בסיסי

```bash
git switch feature-text
git rebase master
```

### interactive rebase

```bash
git rebase -i HEAD~2
```

בעת קונפליקט:

```bash
git status
# תיקון ידני
git add <file>
git rebase --continue
```

הבדל עקרוני מ-merge: rebase כותב היסטוריה מחדש.

---

## reset, revert, stash בקצרה (עם דוגמאות)

### הכנה

```bash
echo "Temp" >> README.md
git add README.md
git commit -m "Temp commit"
````

---

## reset – ביטול קומיט מקומי

### reset --soft

```bash
git reset --soft HEAD~1
```

* History: חוזר אחורה
* Index: נשאר (מוכן לקומיט)
* WD: נשאר

שימוש: שינוי קומיט אחרון

---

### reset --mixed (ברירת מחדל)

```bash
git reset --mixed HEAD~1
```

* History: חוזר
* Index: מתנקה
* WD: נשאר

שימוש: קומיט מוקדם מדי

---

### reset --hard ⚠️

```bash
git reset --hard HEAD~1
```

* History: חוזר
* Index: נוקה
* WD: נמחק

שימוש: רק כשבטוחים או עם reflog

---

## revert – ביטול בטוח

```bash
git revert HEAD
```

* יוצר קומיט חדש שמבטל שינוי
* לא מוחק היסטוריה

### revert של merge

```bash
git revert -m 1 <merge-commit-hash>
```

---

## stash – שמירת עבודה זמנית

```bash
echo "WIP" >> README.md
git stash save "wip"
```

רשימה:

```bash
git stash list
```

החלה:

```bash
git stash apply   # משאיר stash
git stash pop     # מחיל ומוחק
```

שימוש: מעבר ענפים בלי קומיט



## 13. reflog: שחזור טעויות קשות

```bash
git reflog
```

שחזור אחרי reset --hard:

```bash
git reset --hard HEAD@{1}
```

---

## 14. פקודות ניהול נוספות

### git rm

```bash
git rm README.md
```

### git mv

```bash
git mv README.md README-old.md
```

### git clean

אזהרה: מוחק קבצים לא מנוהלים

```bash
git clean -n
git clean -fd
```

### tags

```bash
git tag v1.0
git tag
```

### cherry-pick

```bash
git cherry-pick <commit-hash>
```

### bisect

```bash
git bisect start
git bisect bad
git bisect good <commit>
```

---

## 15. טבלת סיכום פקודות

| קטגוריה | פקודה | תיאור קצר |
|--------|-------|------------|
| Inspect | git status | מצב הריפו |
| Inspect | git log | היסטוריית קומיטים |
| Inspect | git diff | השוואת שינויים |
| Change | git add | הוספה ל-Staging |
| Change | git commit | יצירת קומיט |
| Change | git merge | מיזוג ענפים |
| Branching | git branch | ניהול ענפים |
| Branching | git switch | מעבר ענפים |
| Undo | git reset | ביטול שינויים |
| Undo | git revert | ביטול בטוח |
| Undo | git stash | שמירה זמנית |
| History | git reflog | היסטוריית HEAD |
| History | git rebase | כתיבת היסטוריה מחדש |

