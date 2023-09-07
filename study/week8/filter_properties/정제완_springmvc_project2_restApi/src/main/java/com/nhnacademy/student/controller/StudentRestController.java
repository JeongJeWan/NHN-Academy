package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.request.StudentRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentRepository studentRepository;

    @ResponseBody
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") String studentId) {

        Student student = studentRepository.getStudentById(studentId);

//        return ResponseEntity
//                .ok()
//                .body(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //  등록
    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody StudentRegisterRequest studentRegister, BindingResult bindingResult) {
        Student student = new Student(studentRegister.getId(), studentRegister.getName(), studentRegister.getGender(), studentRegister.getAge());
        studentRepository.save(student);


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentRegisterRequest> update(@PathVariable("studentId") String studentId, @RequestBody StudentRegisterRequest request) {
        Student student = studentRepository.getStudentById(studentId);
        student.setId(request.getId());
        student.setName(request.getName());
        student.setGender(request.getGender());
        student.setAge(request.getAge());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
