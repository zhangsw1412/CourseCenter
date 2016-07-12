package buaa.course.model;

import java.sql.Timestamp;

/**
 * Created by 熊纪元 on 2016/7/10.
 */
public class TeamApplication {
    private int id;
    private int teamId;
    private int userId;
    private Timestamp applyTime;
    private int status;
    private boolean studentDelete = false;
    private boolean leaderDelete = false;

    public TeamApplication() {
		super();
	}

	public TeamApplication(int id, int teamId, int user_id, Timestamp applyTime, int status, boolean studentDelete,
			boolean leaderDelete) {
		super();
		this.id = id;
		this.teamId = teamId;
		this.userId = user_id;
		this.applyTime = applyTime;
		this.status = status;
		this.studentDelete = studentDelete;
		this.leaderDelete = leaderDelete;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isStudentDelete() {
		return studentDelete;
	}

	public void setStudentDelete(boolean studentDelete) {
		this.studentDelete = studentDelete;
	}

	public boolean isLeaderDelete() {
		return leaderDelete;
	}

	public void setLeaderDelete(boolean leaderDelete) {
		this.leaderDelete = leaderDelete;
	}

	@Override
	public String toString() {
		return "TeamApplication [id=" + id + ", teamId=" + teamId + ", userId=" + userId + ", applyTime=" + applyTime
				+ ", status=" + status + ", studentDelete=" + studentDelete + ", leaderDelete=" + leaderDelete + "]";
	}
}
