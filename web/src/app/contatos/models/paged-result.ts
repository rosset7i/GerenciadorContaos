export interface PagedResult<T> {
  totalPages: number;
  content: T[];
}
