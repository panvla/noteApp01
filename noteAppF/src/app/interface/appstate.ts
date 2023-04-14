import { DataState } from '../enum/data-state';

export interface Appstate<T> {
  dataState: DataState;
  data?: T;
  error?: string;
}
