USE good;

DELIMITER $

DROP TRIGGER IF EXISTS AFTER_UPDATE_BOOK_BOSS;

CREATE TRIGGER AFTER_UPDATE_BOOK_BOSS
  AFTER UPDATE ON good.book_boss
  FOR EACH ROW
  BEGIN
    UPDATE cart SET price = new.price
    WHERE bookid = new.bookid AND bossid = new.bossid;
  END $


DROP TRIGGER IF EXISTS AFTER_UPDATE_BOOK;

CREATE TRIGGER AFTER_UPDATE_BOOK
  AFTER UPDATE ON good.book
  FOR EACH ROW
  BEGIN
    UPDATE cart SET bookname = new.bookname
    WHERE bookid = new.id;
  END $

DROP TRIGGER IF EXISTS AFTER_UPDATE_BOOK_BOSS_PICTURE;

CREATE TRIGGER AFTER_UPDATE_BOOK_BOSS_PICTURE
  AFTER UPDATE ON good.book_boss_picture
  FOR EACH ROW
  BEGIN
    UPDATE cart SET bookpicture = new.pictureurl
    WHERE bookid = new.bookid and bossid = new.bossid;
  END $

DROP TRIGGER IF EXISTS AFTER_UPDATE_BOSS;

CREATE TRIGGER AFTER_UPDATE_BOSS
  AFTER UPDATE ON good.boss
  FOR EACH ROW
  BEGIN
    UPDATE cart SET bossname = new.bossname, bosspicture = new.bosspicture
    WHERE bossid = new.id;
  END $

DELIMITER ;