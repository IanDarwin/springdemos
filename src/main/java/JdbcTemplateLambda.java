import java.util.List;

import javax.sql.DataSource;

import model.MusicRecording;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/** Java 8 Lambda Expressions serve a variety of purposes;
 * one of the more common uses is as a concise replacement
 * for Inner Classes. This demo uses a Lambda Expression
 * for a RowMapper<T> to avoid having to write an explicity
 * inner class.
 * @author Ian Darwin
 */
public class JdbcTemplateLambda {
	DataSource dataSource = null;
	
	public List<MusicRecording> getAllRecordings() {
		return new JdbcTemplate(dataSource).query("Select * from MusicRecording", mapper);
	}
	
	// The old way:
	// private RowMapper<MusicRecording> mapper = new RowMapper<MusicRecording> {
	//	public MusicRecording mapRow(ResultSet rs, int rownum) {
	
	// The Java 8 way:
	
	private RowMapper<MusicRecording>  mapper = (rs, rownum) -> {
		MusicRecording rec = new MusicRecording();
		rec.setId(rs.getInt("id"));
		rec.setTitle(rs.getString("title"));
		return rec;
	};
	
	// Note that if the mapper were only used in one place ever, we could
	// write it inline, giving in an even shorter form:
	public List<MusicRecording> getAllRecordingsConcise() {
		return new JdbcTemplate(dataSource).query("Select * from MusicRecording", (rs, rownum) -> {
			MusicRecording rec = new MusicRecording();
			rec.setId(rs.getInt("id"));
			rec.setTitle(rs.getString("title"));
			return rec;
		});
	}
}
