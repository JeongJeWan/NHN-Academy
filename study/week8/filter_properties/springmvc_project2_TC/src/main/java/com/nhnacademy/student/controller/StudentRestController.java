package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.DuplicateStudentIdException;
import com.nhnacademy.student.exception.IdExistException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.request.StudentRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentRepository studentRepository;

    @ResponseBody
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") String studentId) {


        Student student = studentRepository.getStudentById(studentId);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

//        return ResponseEntity
//                .ok()
//                .body(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //  등록
    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody StudentRegisterRequest studentRegister, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        if(studentRepository.existById(studentRegister.getId())) {
            throw new DuplicateStudentIdException(studentRegister.getId());
        }

        Student student = new Student(studentRegister.getId(), studentRegister.getName(), studentRegister.getGender(), studentRegister.getAge());
        studentRepository.save(student);


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> modifyStudent(@PathVariable("studentId") String studentId, @Valid @RequestBody StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult){
        Student student = studentRepository.getStudentById(studentId);
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        if(Objects.isNull(student)){
            throw new StudentNotFoundException();
        }
        //student.setId(studentRegisterRequest.getId());
        student.setName(studentRegisterRequest.getName());
        student.setGender(studentRegisterRequest.getGender());
        student.setAge(studentRegisterRequest.getAge());
        //studentRepository.update(student);
        return new ResponseEntity(HttpStatus.OK);
    }
}
