import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

interface RegisterRequest{
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

interface AuthRequest{
  email: string;
  password: string;
}

export interface AuthResponse{
  token: string;
}
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly apiUrl = 'http://localhost:8080/api/v1/auth';


  constructor(private http: HttpClient) { }

  register(data: RegisterRequest) : Observable<AuthResponse>{
    console.log(data)
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data);

  }

  login(data:AuthRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/authenticate`, data);
  }

  saveToken(token: string){
    localStorage.setItem('auth_token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

  isLoggedIn(): boolean{
    return !!this.getToken()
  }

  logout()
  {
    localStorage.removeItem('auth_token');
  }

}
