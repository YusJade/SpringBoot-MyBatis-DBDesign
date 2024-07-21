package com.yusjade.libms.service;

import com.yusjade.libms.dao.BookMapper;
import com.yusjade.libms.dao.BorrowRecordMapper;
import com.yusjade.libms.dao.UserMapper;
import com.yusjade.libms.pojo.Book;
import com.yusjade.libms.pojo.BorrowRecord;
import com.yusjade.libms.pojo.User;
import jakarta.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BorrowService implements BaseService<Map<String, Object>, BorrowRecord> {

  @Resource
  BorrowRecordMapper borrowRecordMapper;
  @Resource
  BookMapper bookMapper;
  @Resource
  UserMapper userMapper;

  public BorrowRecord getBorrowById(Long id) {
    return borrowRecordMapper.selectByPrimaryKey(id);
  }

  public List<BorrowRecord> list(Map<String, Object> param) {
    Long recordId = (Long) param.get("recordId");
    Long userId = (Long) param.get("userId");
    Long bookId = (Long) param.get("bookId");
//    Long categoryId = (Long) param.get("categoryId");
    Boolean excludeFinished = (Boolean) param.get("excludeFinished");
    return borrowRecordMapper.selectByParamSelective(
        userId,
        bookId,
        excludeFinished);
  }

//  List<BorrowRecord> listBorrowByBook(Long bookId) {
//    return borrowRecordMapper.;
//  }
//
//  List<BorrowRecord> listBorrowByUser(int userId) {
//    Map<String, Object> map = new HashMap<>();
//    map.put("book_id", userId);
//    return borrowDao.selectBorrow(map, true);
//  }
//
//  List<Borrow> listBorrowByCategory(int categoryId) {
//    Map<String, Object> map = new HashMap<>();
//    map.put("book_id", categoryId);
//    return borrowDao.selectBorrow(map, true);
//  }
//
//  List<Borrow> listAllBorrow() {
//    return borrowRecordMapper.selectByPrimaryKey();
//  }
//
//

  /**
   * 保存借阅记录
   *
   * @param record 从控制层传入的新借阅记录
   * @return 插入成功后的借阅记录编号
   * todo: 通过权限限制用户的借阅
   */
  public Long saveBorrow(BorrowRecord record) {
    if (record == null) {
      return 0L;
    }
    User user = userMapper.selectByPrimaryKey(record.getUserId());
    Book book = bookMapper.selectByPrimaryKey(record.getBookId());
    if (user == null) {
      return 0L;
    }
    if (book == null) {
      return 0L;
    }
    if (book.getIsBorrowed() || book.getIsDiscarded()) {
      return 0L;
    }
//    if (user.getPermissionName() == null) {
//      return 0;
//    }
    // 设置借阅日期
    Calendar calendar = Calendar.getInstance();
    Timestamp borrowDate = Timestamp.from(calendar.toInstant());
    // 设置应当归还日期
    calendar.add(Calendar.DATE, 30);
    Timestamp returnDate = Timestamp.from(calendar.toInstant());
    record.setRecordId(null);
    record.setBorrowDate(borrowDate);
    record.setActualReturnDate(null);
    record.setOughtReturnDate(returnDate);

    borrowRecordMapper.insert(record);
    return record.getRecordId();
  }

  public Integer update(BorrowRecord record) {
    if (record == null) {
      return 0;
    }
    Long recordId = record.getRecordId();
    BorrowRecord oldRecord = borrowRecordMapper.selectByPrimaryKey(recordId);
    if (oldRecord == null) {
      return 0;
    }
    return borrowRecordMapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 归还图书
   * @param id
   * @return
   * todo: 实现逾期时的相关逻辑
   */
  public Integer returnBook(Long id) {
    BorrowRecord record = borrowRecordMapper.selectByPrimaryKey(id);
    if (record.getActualReturnDate() != null) {
      return 0;
    }
    Calendar calendar = Calendar.getInstance();
    Timestamp curDate = Timestamp.from(calendar.toInstant());
    // 逾期
    if (record.getOughtReturnDate().compareTo(curDate) < 0) {
      return -1;
    }
    record.setActualReturnDate(curDate);
    return borrowRecordMapper.updateByPrimaryKeySelective(record);
  }

//
//  /**
//   * 续借图书
//   *
//   * @param id 借阅记录 id
//   * @return -3：记录不存在；-2：图书已归还，无法续借；-1：逾期； 0：系统错误或无法更新；1：成功续借
//   */
//  int renewBorrow(int id) {
//    Borrow borrow = borrowDao.selectBorrowById(id);
//    if (borrow == null) {
//      return -3;
//    }
//    if (borrow.getReally_return_date() != null) {
//      return -2; // 图书已归还，无法续借
//    }
//    // 计算续借时是否逾期
//    Calendar calendar = Calendar.getInstance();
//    Timestamp curDate = Timestamp.from(calendar.toInstant());
//    Timestamp returnDate = borrow.getReturn_date();
//    if (curDate.compareTo(returnDate) > 0) {
//      return -1;
//    }
//    // 未逾期则续满
//    User user = userDao.getUserById((long) borrow.getUser_id());
//    calendar.add(Calendar.DATE, Math.toIntExact(user.getMax_borrow_days()));
//    returnDate = Timestamp.from(calendar.toInstant());
//    return borrowDao.updateBorrowById(
//        Map.of("id", borrow.getId(),
//            "return_date", returnDate));
//  }
//
//  /**
//   * 续借图书
//   *
//   * @param id 借阅记录 id
//   * @return -3：记录不存在；-2：图书已归还；-1：逾期； 0：系统错误或无法更新；1：成功续借
//   */
//  int returnBorrow(int id) {
//    Borrow borrow = borrowDao.selectBorrowById(id);
//    if (borrow == null) {
//      return -3;
//    }
//    if (borrow.getReally_return_date() != null) {
//      return -2; // 图书已归还，无需再归还
//    }
//    Calendar calendar = Calendar.getInstance();
//    Timestamp curDate = Timestamp.from(calendar.toInstant());
//    if (borrow.getReturn_date().compareTo(curDate) < 0) {
//      return -1;
//    }
//    return borrowDao.updateBorrowById(
//        Map.of("id", borrow.getId(),
//            "really_return_date", curDate));
//  }
}
