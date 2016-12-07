import java.util.List;

import javax.sql.DataSource;

import model.MusicRecording;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateLambda {
	DataSource dataSource = null;
	
	public List<MusicRecording> getAllRecordings() {
		return new JdbcTemplate(dataSource).query("Select * from MusicRecording", mapper);
	}
	
	private static RowMapper<MusicRecording>  mapper = (rs, rownum) -> {
		MusicRecording rec = new MusicRecording();
		rec.setId(rs.getInt("id"));
		rec.setTitle(rs.getString("title"));
		return rec;
	};
}
