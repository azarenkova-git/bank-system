export type ErrorType<D extends object> = Partial<Record<keyof D, string>>;
