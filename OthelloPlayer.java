/**
 * オセロプレイヤを表すコンストラクタ
 * @author nikami
 */

public interface OthelloPlayer {
    /**
     * 次の手を返すメソッド
     * @param othello オセロ盤面情報を所持したクラス
     * @return 次の手
     */
    abstract public int next (Othello othello);    
}
