package quiz;

import java.util.ArrayList;

/**
 * 학교 클래스는 학생들(ArrayList<Student>)의 평균(avg)과 등수(rank)를 매기는 등 성적을 관리함 따라서, 학교
 * 클래스가 생성(Constructor)되면 입학한 학생들의 정보를 지녀야 함
 */
public class School {
	ArrayList<Student> studentList; // 학생목록 배열을 선언
	double[] ranking;

	// 생성자
	public School() {
		studentList = new ArrayList<Student>(); // 학생목록을 생성
	} // end school Constructor

	// 학생들을 학교에 입학
	public void addStudent(Student student) {
		studentList.add(student);
	}// end addStudent

	// 학생들의 총점을 구하는 함수
	public void setTotalOfStudents() {
		for (int i = 0; i < studentList.size(); i++) {
			int totalScore = 0;
			ArrayList<Subject> subjects = new ArrayList<Subject>();
			subjects = studentList.get(i).subjectList;

			for (Subject s : subjects) {
				totalScore += s.getScorePoint();
			} // end for

			studentList.get(i).total = totalScore;
		} // end for
	} // end setTotalOfStudents

	// 학생들의 평균을 구하는 함수
	public void setAvgOfStudents() {
		for (int i = 0; i < studentList.size(); i++) {
			int avgScore = 0;
			ArrayList<Subject> subjects = new ArrayList<Subject>();

			subjects = studentList.get(i).subjectList;
			avgScore += studentList.get(i).total / subjects.size();

			studentList.get(i).avg = avgScore;
		} // end for
	}// end setAvgOfStudents

	// 학생들의 순위를 구하는 함수
	public void setRankOfStudents() {
		ranking = new double[studentList.size()]; // 학생의 인원수 만큼 배열 생성

		// 랭킹 배열에 학생들의 평균점수 초기화
		for (int i = 0; i < ranking.length; i++) {
			ranking[i] = studentList.get(i).avg;
		}
		for (int i = 0; i < ranking.length; i++) {
			System.out.print("data[" + i + "] = " + ranking[i] + "\t");
		}
		System.out.println();

		int dLen = ranking.length; //7
		
		//length-1 의 의미는 마지막 pivot 은 오른쪽으로 검사할
		//대상이 없음으로 for문을 돌지않게 하기 위한 조치이다.
		for(int i=1; i<dLen; i++) {  
			
			//pivot 값으로 오른쪽으로 계속 비교해주는 for 문이다. 
			for(int j=0; j < (dLen-i); j++) {
				
				//왼쪽에 값이 오른쪽 값보다 크냐?
				if( ranking[j] > ranking[j+1] ) {
					//왼쪽값이 작아야된다. 작은순대로 정렬할것이기 때문에
					//swap 알고리즘 - 현재 왼쪽값을 임시 변수 k 에 저장해둔다.
					double k = ranking[j];  
					ranking[j] = ranking[j+1]; //왼쪽자리에 오른쪽에 값을 저장한다.
					ranking[j+1] = k;		 //오른쪽자리에 왼쪽값을 저장한다.
				}
				
			}
			
		}//end for

	}

	// 학생들의 순위를 전부 보여주는 함수\
	public void showRanking() {
		for (int i = 0; i < ranking.length; i++) {
			System.out.println(ranking[i]);
		}
	}

}
