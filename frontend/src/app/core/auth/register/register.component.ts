import { Component } from '@angular/core';
import {Form, FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {MatFormField} from '@angular/material/input';
import {Role} from '../../model/role.enum';
import {RegisterRequest} from '../../model/user.model';





@Component({
  selector: 'app-register',
  standalone : true,
  imports: [
    MatFormField,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm : FormGroup;
  roles = Object.values(Role);


  constructor(
    private fb : FormBuilder,
    private authService : AuthService,
  ) {

    this.registerForm = this.fb.group({
      firstname : ['',Validators.required],
      lastname : ['',Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['',Validators.required],
      role:['CLIENT']
    })
  }


  onSubmit() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe({
        next: (res) => {
          console.log('Inscription réussie', res);
        },
        error: (err) => {
          console.error('Erreur inscription', err);
        }
      });
    }
  }
}
