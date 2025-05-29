import { Component } from '@angular/core';
import {Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {MatFormField, MatInputModule} from '@angular/material/input';
import {Role} from '../../model/role.enum';
import {RegisterRequest} from '../../model/user.model';
import {MatFormFieldModule} from '@angular/material/form-field';
import {CommonModule} from '@angular/common';
import {MatCard} from '@angular/material/card';
import {MatButton} from '@angular/material/button';





@Component({
  selector: 'app-register',
  standalone : true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,

    MatButton
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
      firstName : ['',Validators.required],
      lastName : ['',Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['',Validators.required]
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
