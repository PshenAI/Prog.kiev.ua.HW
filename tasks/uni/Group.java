package uni;

import java.util.Arrays;

import uni.exceptions.GroupOverflowException;
import uni.exceptions.NoSuchStudentException;

public class Group {

	private Student[] students = new Student[10];
	private String name;

	public Group() {
		super();
	}

	public Group(String name) {
		super();
		this.name = name;
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addStudent(Student student) throws GroupOverflowException {
		if (students[9] != null) {
			throw new GroupOverflowException();
		}
		for (int i = 0; i < students.length; i++) {
			if (students[i] == null) {
				students[i] = student;
				break;
			}
		}
	}

	public void fireStudent(int id) throws NoSuchStudentException {
		for (int i = 0; i < students.length; i++) {
			if (students[i] == null) {
				throw new NoSuchStudentException();
			}
			if (students[i].getId() == id) {
				students[i] = null;
				break;
			}
		}
	}

	public Student searchStudent(String surname) throws NoSuchStudentException {
		Student sought = null;
		for (int i = 0; i < students.length; i++) {
			if (students[i] == null) {
				throw new NoSuchStudentException();
			}
			if (students[i].getSurname().equals(surname)) {
				sought = students[i];
				break;
			}
		}
		return sought;
	}

	public Student[] sortStudents() {
		Student[] sortStud = Arrays.copyOf(students, students.length);

		for (int i = 0; i < sortStud.length && sortStud[i] != null; i++) {
			for (int j = 0; j < students.length && sortStud[j] != null; j++) {
				sortStud = sorterName(sortStud, sortStud[j], j);
			}
		}

		return sortStud;

	}

	public Student[] sorterName(Student[] a, Student b, int c) {
		char[] stand = b.getName().toLowerCase().toCharArray();
		for (int i = 0; i < a.length && a[i] != null; i++) {
			int count = 0;
			char[] standArr = a[i].getName().toLowerCase().toCharArray();
			int[] indStand = new int[stand.length];
			for (int j = 0; j < indStand.length; j++) {
				indStand[j] = indexGetter(stand[j]);
			}
			int[] indStandArr = new int[standArr.length];
			for (int k = 0; k < indStandArr.length; k++) {
				indStandArr[k] = indexGetter(standArr[k]);
			}
			if (b.getName().equals(a[i].getName())) {
				if (sorterSurname(b.getSurname(), a[i].getSurname()) == true) {
					Student temp = a[i];
					a[i] = b;
					a[c] = temp;
					break;
				}
			}
			if (indStand[count] == indStandArr[count] && count < indStand.length - 1) {
				for (; indStand[count] == indStandArr[count] && count < indStand.length - 1;) {
					count += 1;
				}
			}
			if (indStand[count] < indStandArr[count]) {
				Student temp = a[i];
				a[i] = b;
				a[c] = temp;
				break;
			}
		}
		return a;
	}

	public boolean sorterSurname(String a, String b) {
		boolean boo = false;
		char[] stand = b.toCharArray();
		char[] standArr = a.toCharArray();
		int count = 0;
		int[] indStand = new int[stand.length];
		for (int j = 0; j < indStand.length; j++) {
			indStand[j] = indexGetter(stand[j]);
		}
		int[] indStandArr = new int[standArr.length];
		for (int k = 0; k < indStandArr.length; k++) {
			indStandArr[k] = indexGetter(standArr[k]);
		}
		if (indStand[count] == indStandArr[count] && count < indStand.length - 1) {
			for (; indStand[count] == indStandArr[count] && count < indStand.length - 1;) {
				count += 1;
			}
		}
		if (indStand[count] < indStandArr[count]) {
			boo = true;
		}
		return boo;

	}

	public int indexGetter(char exm) {
		int fix = 0;
		char[] alph = { 'a', 'b', 'c', 'd', 'e', 'f', 'j', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };
		for (int i = 0; i < alph.length; i++) {
			char temp = exm;
			if (temp == alph[i]) {
				fix = i;
				break;
			}
		}
		return fix;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("The group is empty!");
		if (students[0] != null) {
			Student[] arr = sortStudents();
			sb.setLength(0);
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != null) {
					sb.append(arr[i].getInfo() + "\n");
				} else {
					break;
				}
			}
		}
		return sb.toString();
	}
}